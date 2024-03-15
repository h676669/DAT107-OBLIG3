package Prosjekt;

import jakarta.persistence.Entity;

@Entity
public class Ansatt {

    @Id
    private int ansattID;
    private String brukernavn, fornavn, etternavn;
    private double manedslonn;


}
