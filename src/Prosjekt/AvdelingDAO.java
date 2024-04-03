package Prosjekt;

import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AvdelingDAO {
    private EntityManagerFactory emf;

    public AvdelingDAO() {
        emf = Persistence.createEntityManagerFactory("Avdeling");
    }

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }

    public List<Avdeling> finnAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Avdeling> query = em.createQuery("select t from Avdeling t", Avdeling.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void lagreNyAvdeling(String avdeling_navn) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        AnsattDAO ansattDAO = new AnsattDAO();
        try {

            TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a WHERE a.avdeling_navn = :navn", Avdeling.class);
            query.setParameter("navn", avdeling_navn);
            List<Avdeling> existingAvdelinger = query.getResultList();

            if (!existingAvdelinger.isEmpty()) {
                System.out.println("Avdeling eksisterer allerede.");
                return;
            }

            tx.begin();

            List<Ansatt> AlleAnsatte = ansattDAO.finnAlleAnsatt();
            int nySjefID = -1;
            Ansatt sjef = null;

            for (Ansatt potensiellSjef : AlleAnsatte) {
                if (potensiellSjef != null && potensiellSjef.getAvdeling().getLe_boss_id() != potensiellSjef.getAnsatt_id()) {
                    nySjefID = potensiellSjef.getAnsatt_id();
                    sjef = potensiellSjef;
                    break;
                }
            }

            if (sjef != null) {
                Avdeling nyAvdeling = new Avdeling(avdeling_navn, new ArrayList<>(), nySjefID);
                em.persist(nyAvdeling);
                ansattDAO.oppdaterAnsatt(sjef.getAnsattID(), nyAvdeling.getAvdelingID());
                em.merge(sjef);
            } else {
                System.out.println("Ingen passende sjef funnet, kan ikke opprette ny avdeling.");
            }

            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
            ansattDAO.close();
        }
    }

    public void leggTilAnsatt(Ansatt nyAnsatt, int avdeling_id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            AnsattDAO ansattDAO = new AnsattDAO();
            List<Ansatt> AlleAnsatte = ansattDAO.finnAlleAnsatt();

            if (nyAnsatt.getAnsattID() != finnAvdelingMedId(nyAnsatt.getAvdeling().getAvdelingID()).getLe_boss_id()) {
                Avdeling managedAvdeling = finnAvdelingMedId(avdeling_id);
                List<Ansatt> alleAnsatteAvdeling = managedAvdeling.getAvdelingAnsatt();
                alleAnsatteAvdeling.add(nyAnsatt);
                nyAnsatt.setAvdelingID(avdeling_id);
                oppdaterAvdeling(avdeling_id, alleAnsatteAvdeling, managedAvdeling.getAvdelingsnavn(), managedAvdeling.getLe_boss_id());
            }
            tx.commit();
            ansattDAO.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void oppdaterAvdeling(int avdeling_id, List<Ansatt> nyeAnsatte, String navn, int Sjef_id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Avdeling managedAvdeling = finnAvdelingMedId(avdeling_id);
            managedAvdeling.setAvdelingAnsatt(nyeAnsatte);
            managedAvdeling.setAvdelingsnavn(navn);
            managedAvdeling.setLe_boss_id(Sjef_id);
            em.merge(managedAvdeling);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
