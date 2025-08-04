package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtentiRepository extends JpaRepository<Utenti, Long> {

    Optional<Utenti> findByEmail(String email);
    Optional<Utenti> findByUsername(String username);
}
