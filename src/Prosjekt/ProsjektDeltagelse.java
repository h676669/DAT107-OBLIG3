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

    public int getProsjektID() {
        return prosjekt_id;
    }

    public void setProsjektID(int prosjektID) {
        this.prosjekt_id = prosjektID;
    }

    public String getPRolle() {
        return p_rolle;
    }

    public int getProsjektBeskrivelse() {
        return prosjekt_beskrivelse;
    }

    public void setProsjektBeskrivelse(int prosjektBeskrivelse) {
        this.prosjekt_beskrivelse = prosjektBeskrivelse;
    }

}
