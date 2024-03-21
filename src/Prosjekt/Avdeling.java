package Prosjekt;

import jakarta.persistence.*;

import javax.xml.validation.Schema;

@Entity
@Table(schema = "Dat107Oblig3")

public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int avdelingID;

    @Column(columnDefinition = "TEXT")
    private String avdelingsnavn;

    @Column(columnDefinition = "TEXT")
    private int avdelingAnsatt;

    @Column(columnDefinition = "INT")
    private int le_boss_id;


    public int getAvdelingID() {
        return avdelingID;
    }

    public void setAvdelingID(int avdelingID) {
        this.avdelingID = avdelingID;
    }

    public String getAvdelingsnavn() {
        return avdelingsnavn;
    }

    public void setAvdelingsnavn(String avdelingsnavn) {
        this.avdelingsnavn = avdelingsnavn;
    }

    public int getAvdelingAnsatt() {
        return avdelingAnsatt;
    }

    public void setAvdelingAnsatt(int avdelingAnsatt) {
        this.avdelingAnsatt = avdelingAnsatt;
    }

    public int getLe_boss_id() {
        return le_boss_id;
    }

    public void setLe_boss_id(int le_boss_id) {
        this.le_boss_id = le_boss_id;
    }
}
