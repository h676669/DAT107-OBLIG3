package Prosjekt;

import jakarta.persistence.*;

@Entity
@Table(schema = "Dat107Oblig3")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosjektID;

    @Column(columnDefinition = "TEXT")
    private String navn, beskrivelse;

    //her skal det være for ting for ansatte i prosjektet
}
