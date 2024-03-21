package Prosjekt;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class KoblingsTabellDAO {


    private EntityManagerFactory emf;

    public KoblingsTabellDAO() {
        emf = Persistence.createEntityManagerFactory("KoblingsTabell");
    }



}
