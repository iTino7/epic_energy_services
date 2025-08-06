package epic_energy_services.bw2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruolo")
@Getter
@NoArgsConstructor
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_ruolo")
    private String nomeRuolo;

    @JsonIgnore
    @ManyToMany(mappedBy = "ruoli")
    private Set<User> utenti = new HashSet<>();

    public void setNomeRuolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }

    public void setUtenti(Set<User> utenti) {
        this.utenti = utenti;
    }

    public Ruolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }
}