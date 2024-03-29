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

    @OneToMany(mappedBy = "prosjekt") // Corrected mapping field name
    private List<ProsjektDeltagelse> ProsjektListe;

    public Prosjekt(){
        
    }

}
