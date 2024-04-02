package Prosjekt;

import jakarta.persistence.*;

import java.util.List;

public class ProsjektDAO {


    private EntityManagerFactory emf;

    public ProsjektDAO() {
        emf = Persistence.createEntityManagerFactory("Prosjekt");
    }

    public void lagreNyttProsjekt(String prosjektNavn, String prosjekt_beskrivelse, List<ProsjektDeltagelse> prosjektliste){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(new Prosjekt(prosjekt_beskrivelse, prosjektNavn, prosjektliste));
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
    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prosjekt.class, id);
        } finally {
            em.close();
        }
    }

    public List<Prosjekt> finnAlleProsjekt() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Prosjekt> query = em.createQuery("select t from Prosjekt t", Prosjekt.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}