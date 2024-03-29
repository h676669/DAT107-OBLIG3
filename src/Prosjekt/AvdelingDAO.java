package Prosjekt;

import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.util.List;

public class AvdelingDAO {
    private EntityManagerFactory emf;

    public AvdelingDAO(){
        emf = Persistence.createEntityManagerFactory("Avdeling");
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

    public void lagreNyAvdeling(int avdeling_id,String avdeling_navn){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            

            tx.commit();
        }
        catch (Throwable e){
            e.printStackTrace();
            if(tx.isActive()){
                tx.rollback();
            }
        }
        finally {
            em.close();
        }
    }
    public void skrivUtAlleAnsatteAvdeling(int id){
        EntityManager em = emf.createEntityManager();
        try {
           Avdeling managedAvdeling = finnAvdelingMedId(id);
           managedAvdeling.skrivUtAlleAnsatt();
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
