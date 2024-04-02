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

    @ManyToOne
    @JoinColumn(name = "pd_ansatt")
    private Ansatt ansatt;
    @ManyToOne
    @JoinColumn(name = "pd_prosjekt")
    private Prosjekt prosjekt;

    private int timer;

    public int getProsjektdeltagelse_id() {
        return prosjektdeltagelse_id;
    }

    public void setProsjektdeltagelse_id(int prosjektdeltagelse_id) {
        this.prosjektdeltagelse_id = prosjektdeltagelse_id;
    }

    public String getP_rolle() {
        return p_rolle;
    }

    public void setP_rolle(String p_rolle) {
        this.p_rolle = p_rolle;
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

    public ProsjektDeltagelse(){

    }
    public ProsjektDeltagelse(int prosjektdeltagelse_id, String p_rolle, Ansatt ansatt, Prosjekt prosjekt, int timer){
        this.p_rolle = p_rolle;
        this.prosjekt = prosjekt;
        this.prosjektdeltagelse_id = prosjektdeltagelse_id;
        this.ansatt = ansatt;
        this.timer = timer;
    }
}
