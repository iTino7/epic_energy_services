package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
public class IndirizzoSedeOperativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;

    @ManyToOne
    private Comune comune;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private IndirizzoSedeOperativa() {
    }

    public IndirizzoSedeOperativa(String via, String civico, String localita, String cap, Comune comune, Cliente cliente) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
