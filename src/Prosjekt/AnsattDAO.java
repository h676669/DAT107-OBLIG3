package Prosjekt;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AnsattDAO {

    private EntityManagerFactory emf;

    public AnsattDAO(){
        emf = Persistence.createEntityManagerFactory("AnsattPU");
    }

}
