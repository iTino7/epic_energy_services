package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndirizzoSedeLegaleRepository extends JpaRepository<IndirizzoSedeLegale, Long> {
    List<IndirizzoSedeLegale> findByCap(String cap);

    //List<IndirizzoSedeLegale> findByNomeComune(String nomeComune);

    Optional<IndirizzoSedeLegale> findByViaAndCivico(String via, String Civico);
}
