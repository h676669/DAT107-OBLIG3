package Prosjekt;

import jakarta.persistence.*;

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
    public Ansatt finnAnsattMedBrukernanv(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("select t from Ansatt t where t.brukernavn like :brukernavn", Ansatt.class);
            query.setParameter("brukernavn",brukernavn);
            return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater
        } finally {
            em.close();
        }
    }
    public List<Ansatt> finnAlleAnsatt(){
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Ansatt> query = em.createQuery("select t from Ansatt t", Ansatt.class);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
    public void oppdaterAnsatt(int id, String stilling, double lonn){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ansatt managedAnsatt = finnAnsattMedId(id);
            managedAnsatt.setManedslonn(lonn);
            managedAnsatt.setStilling(stilling);
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

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}