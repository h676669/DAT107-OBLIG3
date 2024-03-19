package Prosjekt;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Dat107Oblig3")


public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansattID;

    @Column(columnDefinition = "TEXT")
    private String brukernavn, fornavn, etternavn, stilling;

    @Temporal(TemporalType.DATE)
    private LocalDate ansettelsesDato; //?
    private double manedslonn;

    @ManyToOne
    @JoinColumn(name = "ansattID")
    private Prosjekt prosjekt;
}
