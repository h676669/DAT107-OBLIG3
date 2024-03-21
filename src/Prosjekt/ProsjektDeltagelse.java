package Prosjekt;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "Dat107Oblig3")

public class ProsjektDeltagelse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosjekt_id;

    @Column(columnDefinition = "TEXT")
    private String prosjekt_navn;

    @Column(columnDefinition = "TEXT")
    private int prosjekt_beskrivelse;

    @OneToMany
    private List<ProsjektDeltagelse> ansatte;
}
