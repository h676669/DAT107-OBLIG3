package Prosjekt;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "Dat107Oblig3")


public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansatt_id, avdelingID, prosjektdeltagelseID;

    @Column(columnDefinition = "TEXT")
    private String brukernavn, fornavn, etternavn, stilling;

    @Temporal(TemporalType.DATE)
    private Date ansettelsesDato; //?
    private double manedslonn;
    @OneToMany(mappedBy = "ansatt") // Corrected mapping field name
    private List<ProsjektDeltagelse> prosjektDeltagelse;

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
    public Ansatt(){

    }
    public Ansatt(String brukernavn, String fornavn,
                  String etternavn, String stilling, Date ansettelsesDato,
                  Double manedslonn, int avdelingID, int prosjektdeltagelseID) {
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.brukernavn = brukernavn;
        this.stilling = stilling;
        this.ansettelsesDato = ansettelsesDato;
        this.manedslonn = manedslonn;
        this.avdelingID = avdelingID;
        this.prosjektdeltagelseID = prosjektdeltagelseID;
    }

    public int getAnsattID() {
        return ansatt_id;
    }

    public void setAnsattID(int ansattID) {
        this.ansatt_id = ansattID;
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

    public Date getAnsettelsesDato() {
        return ansettelsesDato;
    }

    public void setAnsettelsesDato(Date ansettelsesDato) {
        this.ansettelsesDato = ansettelsesDato;
    }

    public double getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(double manedslonn) {
        this.manedslonn = manedslonn;
    }

    public int getAnsatt_id() {
        return ansatt_id;
    }

    public void setAnsatt_id(int ansatt_id) {
        this.ansatt_id = ansatt_id;
    }

    public int getAvdelingID() {
        return avdelingID;
    }

    public void setAvdelingID(int avdelingID) {
        this.avdelingID = avdelingID;
    }

    public int getProsjektdeltagelseID() {
        return prosjektdeltagelseID;
    }

    public void setProsjektdeltagelseID(int prosjektdeltagelseID) {
        this.prosjektdeltagelseID = prosjektdeltagelseID;
    }

    public List<Prosjekt.ProsjektDeltagelse> getProsjektDeltagelse() {
        return prosjektDeltagelse;
    }

    public void setProsjektDeltagelse(List<Prosjekt.ProsjektDeltagelse> prosjektDeltagelse) {
        this.prosjektDeltagelse = prosjektDeltagelse;
    }
}
