package epic_energy_services.bw2.repository;

import epic_energy_services.bw2.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
}
