package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByNomeRuolo(String nomeRuolo);
}
