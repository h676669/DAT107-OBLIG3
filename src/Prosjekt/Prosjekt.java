package Prosjekt;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "Dat107Oblig3")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosjekt_id;

    @Column(columnDefinition = "TEXT")
    private String prosjekt_navn, prosjekt_beskrivelse;

    @OneToMany(mappedBy = "prosjekt") // Corrected mapping field name
    private List<ProsjektDeltagelse> prosjektListe;

    public Prosjekt() {

    }

    public Prosjekt(String prosjekt_beskrivelse, String prosjekt_navn) {
        this.prosjekt_beskrivelse = prosjekt_beskrivelse;
        this.prosjekt_navn = prosjekt_navn;
        this.prosjektListe = new ArrayList<>();
    }

    public int getProsjekt_id() {
        return prosjekt_id;
    }

    public void setProsjekt_id(int prosjekt_id) {
        this.prosjekt_id = prosjekt_id;
    }

    public String getProsjekt_navn() {
        return prosjekt_navn;
    }

    public void setProsjekt_navn(String prosjekt_navn) {
        this.prosjekt_navn = prosjekt_navn;
    }

    public String getProsjekt_beskrivelse() {
        return prosjekt_beskrivelse;
    }

    public void setProsjekt_beskrivelse(String prosjekt_beskrivelse) {
        this.prosjekt_beskrivelse = prosjekt_beskrivelse;
    }

    public List<ProsjektDeltagelse> getProsjektListe() {
        return prosjektListe;
    }

    public void setProsjektListe(List<ProsjektDeltagelse> prosjektListe) {
        this.prosjektListe = prosjektListe;
    }

}
