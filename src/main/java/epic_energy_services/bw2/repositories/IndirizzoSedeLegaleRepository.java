package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndirizzoSedeLegaleRepository extends JpaRepository<IndirizzoSedeLegale, Long> {
    List<IndirizzoSedeLegale> findByCap(String cap);

    Optional<IndirizzoSedeLegale> findByComune_Denominazione(String denominazione);

    @Query("SELECT sl FROM IndirizzoSedeLegale sl WHERE sl.via = :via AND sl.civico = :civico AND sl.localita = :localita")
    Optional<IndirizzoSedeLegale> findByViaAndCivicoAndLocalita(String via, String civico, String localita);
}
