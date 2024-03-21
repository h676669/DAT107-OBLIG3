package Prosjekt;


import jakarta.persistence.*;

@Entity
@Table(schema = "Dat107Oblig3")

public class ProsjektDeltagelse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosjektdeltagelse_id;

    @Column(columnDefinition = "TEXT")
    private String p_rolle;

    @ManyToOne // This indicates the many-to-one side of the relationship
    @JoinColumn(name = "pd_ansatt") // This should match the foreign key in the database
    private Ansatt ansatt;
    @ManyToOne
    @JoinColumn(name = "pd_prosjekt")
    private Prosjekt prosjekt;

    public int getProsjektdeltagelse_id() {
        return prosjektdeltagelse_id;
    }

    public void setProsjektdeltagelse_id(int prosjektID) {
        this.prosjektdeltagelse_id = prosjektID;
    }

    public String getPRolle() {
        return p_rolle;
    }

    public void setPRolle(String pRolle) {
        this.p_rolle = pRolle;
    }
}
