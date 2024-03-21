package Prosjekt;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProsjektDeltagelseDAO {


    private EntityManagerFactory emf;

    public ProsjektDeltagelseDAO() {
        emf = Persistence.createEntityManagerFactory("ProsjektDeltagelse");
    }



}
