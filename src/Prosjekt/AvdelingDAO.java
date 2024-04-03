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
        try {

            tx.begin();
            AnsattDAO skalBliSjef = new AnsattDAO();

            List<Ansatt> AlleAnsatte = skalBliSjef.finnAlleAnsatt();
            int nySjefID = 0;
            Ansatt sjef = null;

            for (Ansatt nySjef : AlleAnsatte) {
                if (nySjef != null && nySjef.getAnsatt_id() != finnAvdelingMedId(nySjef.getAvdeling().getAvdelingID()).getLe_boss_id()) {
                    nySjefID = nySjef.getAnsatt_id();
                    sjef = nySjef;
                    break;
                }
            }
            Avdeling nyAvdeling = null;
            List<Ansatt> nyeAnsatte = new ArrayList<>();
            if (sjef != null) {
                nyeAnsatte.add(sjef);
                nyAvdeling = new Avdeling(avdeling_navn, nyeAnsatte, nySjefID);
                skalBliSjef.oppdaterAnsatt(nySjefID, nyAvdeling.getAvdelingID());
            }
            if (nyAvdeling != null) {
                em.persist(nyAvdeling);
            }

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

    public void skrivUtAlleAnsatteAvdeling(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Avdeling managedAvdeling = finnAvdelingMedId(id);
            managedAvdeling.skrivUtAlleAnsatt();
        } finally {
            em.close();
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
