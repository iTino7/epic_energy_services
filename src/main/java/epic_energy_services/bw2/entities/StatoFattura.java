package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stato_fatture")
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String stato;


    public StatoFattura() {
    }

    public StatoFattura(String stato) {
        this.stato = stato;
    }

    public long getId() {
        return id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", stato='" + stato + '\'' +
                '}';
    }
}
