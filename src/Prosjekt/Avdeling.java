package Prosjekt;

import jakarta.persistence.*;

import javax.xml.validation.Schema;

@Entity
@Table(schema = "Dat107Oblig3")

public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int avdeling_id;

    @Column(columnDefinition = "TEXT")
    private String avdeling_navn;


    @Column(columnDefinition = "INT")
    private int avdeling_ansatt;

    @Column(columnDefinition = "INT")
    private int le_boss_id;

    public Avdeling(int avdelingId, String avdelingNavn, int avdelingAnsatt, int leBossId) {
        avdeling_id = avdelingId;
        avdeling_navn = avdelingNavn;
        avdeling_ansatt = avdelingAnsatt;
        le_boss_id = leBossId;
    }

    public Avdeling() {

    }

    public int getAvdelingID() {
        return avdeling_id;
    }

    public void setAvdelingID(int avdelingID) {
        this.avdeling_id = avdelingID;
    }

    public String getAvdelingsnavn() {
        return avdeling_navn;
    }

    public void setAvdelingsnavn(String avdelingsnavn) {
        this.avdeling_navn = avdelingsnavn;
    }

    public int getAvdelingAnsatt() {
        return avdeling_ansatt;
    }

    public void setAvdelingAnsatt(int avdelingAnsatt) {
        this.avdeling_ansatt = avdelingAnsatt;
    }

    public int getLe_boss_id() {
        return le_boss_id;
    }

    public void setLe_boss_id(int le_boss_id) {
        this.le_boss_id = le_boss_id;
    }
}
