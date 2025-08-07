package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
public class IndirizzoSedeOperativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private String località;
    private String cap;

    @ManyToOne
    private Comune comune;

    private IndirizzoSedeOperativa() {
    }

    public IndirizzoSedeOperativa(String via, String civico, String località, String cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
        this.comune = comune;
    }

    public Long getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }
}
