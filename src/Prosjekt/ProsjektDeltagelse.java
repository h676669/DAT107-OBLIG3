package Prosjekt;


import jakarta.persistence.*;

@Entity
@Table(schema = "Dat107Oblig3")

public class ProsjektDeltagelse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pd_id;

    @Column(columnDefinition = "TEXT")
    private String pd_rolle;

    @ManyToOne
    @JoinColumn(name = "pd_ansatt")
    private Ansatt ansatt;
    @ManyToOne
    @JoinColumn(name = "pd_prosjekt")
    private Prosjekt prosjekt;

    private int timer;

    public ProsjektDeltagelse() {

    }

    public ProsjektDeltagelse(String pd_rolle, Ansatt ansatt, Prosjekt prosjekt) {
        this.pd_rolle = pd_rolle;
        this.prosjekt = prosjekt;
        this.ansatt = ansatt;
        this.timer = 0;
    }

    public int getProsjektdeltagelse_id() {
        return pd_id;
    }

    public void setProsjektdeltagelse_id(int pd_id) {
        this.pd_id = pd_id;
    }

    public String getP_rolle() {
        return pd_rolle;
    }

    public void setP_rolle(String pd_rolle) {
        this.pd_rolle = pd_rolle;
    }

    public Ansatt getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(Ansatt ansatt) {
        this.ansatt = ansatt;
    }

    public Prosjekt getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
