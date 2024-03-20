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
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
    public Ansatt(){

    }
    public Ansatt(Prosjekt prosjekt, String brukernavn, String fornavn,
                  String etternavn, String stilling, LocalDate ansettelsesDato,
                  Double manedslonn) {
        this.prosjekt = prosjekt;
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.brukernavn = brukernavn;
        this.stilling = stilling;
        this.ansettelsesDato = ansettelsesDato;
        this.manedslonn = manedslonn;
    }
}
