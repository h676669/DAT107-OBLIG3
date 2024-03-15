package Prosjekt;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Ansatt")

public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansattID;

    @Column(columnDefinition = "TEXT")
    private String brukernavn, fornavn, etternavn, stilling;

    @Temporal(TemporalType.DATE)
    private LocalDate ansettelsesDato; //?
    private double manedslonn;

}
