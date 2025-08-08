package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.StatoFattura;
import epic_energy_services.bw2.entities.StatoFatturaTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

    StatoFattura findByStato(StatoFatturaTypes stato);
}
