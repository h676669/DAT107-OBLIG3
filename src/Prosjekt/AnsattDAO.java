package Prosjekt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AnsattDAO {

    private EntityManagerFactory emf;

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("Ansatt");
    }

    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("select t from Ansatt t where t.brukernavn like :brukernavn", Ansatt.class);
            query.setParameter("brukernavn", brukernavn);
            return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater
        } finally {
            em.close();
        }
    }

    public List<Ansatt> finnAlleAnsatt() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("select t from Ansatt t", Ansatt.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void oppdaterAnsatt(int id, String stilling, double lonn, int avdeling) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ansatt managedAnsatt = finnAnsattMedId(id);
            managedAnsatt.setManedslonn(lonn);
            managedAnsatt.setStilling(stilling);
            if (managedAnsatt.getAnsatt_id() != managedAnsatt.getAvdeling().getLe_boss_id()) {
                managedAnsatt.setAvdelingID(avdeling);
            }
            em.persist(managedAnsatt);
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

    public void oppdaterAnsatt(int id, String stilling) {
        Ansatt ansatt = finnAnsattMedId(id);
        oppdaterAnsatt(id, stilling, ansatt.getManedslonn(), ansatt.getAvdeling().getAvdelingID());
    }
    public void oppdaterAnsatt(int id, double lonn) {
        Ansatt ansatt = finnAnsattMedId(id);
        oppdaterAnsatt(id, ansatt.getStilling(), lonn, ansatt.getAvdeling().getAvdelingID());
    }
    public void oppdaterAnsatt(int id, String stilling, double lonn) {
        Ansatt ansatt = finnAnsattMedId(id);
        oppdaterAnsatt(id, stilling, lonn, ansatt.getAvdeling().getAvdelingID());
    }
    public void oppdaterAnsatt(int id, int avdelingID) {
        Ansatt ansatt = finnAnsattMedId(id);
        oppdaterAnsatt(id, ansatt.getStilling(), ansatt.getManedslonn(), avdelingID);
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }

    public void leggTilNyAnsatt(Ansatt nyAnsatt) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nyAnsatt);
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

}