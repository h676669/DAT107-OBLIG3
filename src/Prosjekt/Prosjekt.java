package Prosjekt;

import jakarta.persistence.*;

@Entity
@Table(schema = "Dat107Oblig3")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int p_id;

    @Column(columnDefinition = "TEXT")
    private String p_navn, p_beskrivelse;

    //her skal det være for ting for ansatte i prosjektet
}
