package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stato_fatture")
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private StatoFatturaTypes stato;


    public StatoFattura() {
    }

    public StatoFattura(StatoFatturaTypes stato) {
        this.stato = stato;
    }

    public long getId() {
        return id;
    }

    public StatoFatturaTypes getStato() {
        return stato;
    }

    public void setStato(StatoFatturaTypes stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", stato=" + stato +
                '}';
    }
}
