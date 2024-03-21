package Prosjekt;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "Dat107Oblig3")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosjekt_id;

    @Column(columnDefinition = "TEXT")
    private String prosjekt_navn, prosjekt_beskrivelse;

    //her skal det være for ting for ansatte i prosjektet
    @OneToMany(mappedBy = "prosjekt", fetch = FetchType.EAGER)
    private List<Ansatt> ListeAnsatt;

    public void leggTilAnsatt(Ansatt ny) {
        ListeAnsatt.add(ny);
    }
}
