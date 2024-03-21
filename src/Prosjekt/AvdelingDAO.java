package Prosjekt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AvdelingDAO {
    private EntityManagerFactory emf;

    public AvdelingDAO(){
        emf = Persistence.createEntityManagerFactory("Ansatt");
    }
    public Avdeling finnAvdelingMedId(int id){
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }
    public List<Avdeling> finnAlleAvdelinger(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Avdeling> query = em.createQuery("select t from Avdeling t", Avdeling.class);
            return query.getResultList();
        }
        finally {
        em.close();
        }
    }
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
    
}
