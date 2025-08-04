package epic_energy_services.bw2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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

    public User(String username, String email, String password, String nome, String cognome, String avatar, Set<Ruolo> ruoli) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = avatar;
        this.ruoli = ruoli;
    }


}
