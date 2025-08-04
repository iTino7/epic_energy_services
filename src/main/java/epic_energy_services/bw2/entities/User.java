package epic_energy_services.bw2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "utenti_ruolo",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_ruolo")
    )
    private Set<Ruolo> ruoli = new HashSet<>();
}
