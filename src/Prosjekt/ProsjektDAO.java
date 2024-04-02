package Prosjekt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProsjektDAO {


    private EntityManagerFactory emf;

    public ProsjektDAO() {
        emf = Persistence.createEntityManagerFactory("Prosjekt");
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