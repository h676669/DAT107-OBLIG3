package Prosjekt;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansattID;

    @Column(columnDefinition = "TEXT")
    private String brukernavn, fornavn, etternavn, stilling;

    @Temporal(TemporalType.DATE)
    private Date ansettelsesDato; //?
    private double manedslonn;
    
}
