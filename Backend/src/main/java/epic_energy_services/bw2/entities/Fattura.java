package epic_energy_services.bw2.entities;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate data;
    private double importo;
    private int numero;

    @ManyToOne
    @JoinColumn(name = "stato_id")
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura() {
    }

    public Fattura(LocalDate data, double importo, int numero, StatoFattura statoFattura, Cliente cliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.statoFattura = statoFattura;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatoFattura getStatoFattura() {
        return statoFattura;
    }

    public void setStatoFattura(StatoFattura statoFattura) {
        this.statoFattura = statoFattura;
    }

    @Override
    public String toString() {
        return "Fattura{" +
                "id=" + id +
                ", data=" + data +
                ", importo=" + importo +
                ", numero=" + numero +
                ", cliente=" + cliente +
                '}';
    }
}
