package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndirizzoSedeOperativaRepository extends JpaRepository<IndirizzoSedeOperativa, Long> {
    List<IndirizzoSedeOperativa> findByCap(String cap);

    Optional<IndirizzoSedeOperativa> findByComune_Denominazione(String denominazione);

    @Query("SELECT so FROM IndirizzoSedeOperativa so WHERE so.via = :via AND so.civico = :civico AND so.localita = :localita")
    Optional<IndirizzoSedeOperativa> findByViaAndCivicoAndLocalita(String via, String civico, String localita);
}

