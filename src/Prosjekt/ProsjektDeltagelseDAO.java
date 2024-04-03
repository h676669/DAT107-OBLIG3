package Prosjekt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProsjektDeltagelseDAO {


    private EntityManagerFactory emf;

    public ProsjektDeltagelseDAO() {
        emf = Persistence.createEntityManagerFactory("ProsjektDeltagelse");
    }

    public void lagreNyProsjektDeltagelse(String rolle, Ansatt ansatt, Prosjekt prosjekt) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(new ProsjektDeltagelse(rolle, ansatt, prosjekt));
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

    public ProsjektDeltagelse finnProsjektDeltagelse(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ProsjektDeltagelse.class, id);
        } finally {
            em.close();
        }
    }

    public void leggTilTimer(int id, int timer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ProsjektDeltagelse managedProsjektdeltagelse = finnProsjektDeltagelse(id);
            managedProsjektdeltagelse.setTimer(managedProsjektdeltagelse.getTimer() + timer);
            em.merge(managedProsjektdeltagelse);
            tx.commit();
        } catch (Throwable throwable) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void registrerPDAnsatt() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            //noe her
            tx.commit();
        }
        finally {
            em.close();
        }
    }




}
