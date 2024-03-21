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
    private String p_rolle;

    @Column(columnDefinition = "TEXT")
    private int prosjekt_beskrivelse;

    @ManyToOne // This indicates the many-to-one side of the relationship
    @JoinColumn(name = "pd_ansatt") // This should match the foreign key in the database
    private Ansatt ansatt;

}
