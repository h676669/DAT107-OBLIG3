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

    public int getAnsattID() {
        return ansattID;
    }

    public void setAnsattID(int ansattID) {
        this.ansattID = ansattID;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public LocalDate getAnsettelsesDato() {
        return ansettelsesDato;
    }

    public void setAnsettelsesDato(LocalDate ansettelsesDato) {
        this.ansettelsesDato = ansettelsesDato;
    }

    public double getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(double manedslonn) {
        this.manedslonn = manedslonn;
    }

    public Prosjekt.Prosjekt getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(Prosjekt.Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }
}
