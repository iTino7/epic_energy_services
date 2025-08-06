package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IndirizzoSedeOperativaRepository extends JpaRepository<IndirizzoSedeOperativa, Long> {
    List<IndirizzoSedeOperativa> findByCap(String cap);

    List<IndirizzoSedeOperativa> findByNomeComune(String nomeComune);

    Optional<IndirizzoSedeOperativa> findByViaAndCivico(String via, String civico);
}
