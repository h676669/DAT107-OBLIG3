package Prosjekt;

import jakarta.persistence.*;

@Entity
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansattID;

    @Column(columnDefinition = "TEXT")
    private String brukernavn, fornavn, etternavn, stilling;

    private double manedslonn;

    public int getAnsattID() {
        return ansattID;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
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

    public double getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(double manedslonn) {
        this.manedslonn = manedslonn;
    }
}
