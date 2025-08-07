package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
public class IndirizzoSedeLegale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;

    @ManyToOne
    private Comune comune;

    private IndirizzoSedeLegale() {
    }

    public IndirizzoSedeLegale(String via, String civico, String localita, String cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
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

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
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
