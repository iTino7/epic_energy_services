package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.entities.Fattura;
import epic_energy_services.bw2.entities.StatoFatturaTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    Optional<Fattura> findByNumero(int numero);

    @Query("SELECT f FROM Fattura f WHERE f.cliente = :cliente")
    Optional<List<Fattura>> findByCliente (@Param("cliente") Cliente cliente);

    @Query("SELECT f FROM Fattura f WHERE f.statoFattura = :statoFattura")
    List<Fattura> filterByStato (@Param("statoFattura") StatoFatturaTypes statoFattura);

    List<Fattura> findByData (LocalDate data);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    List<Fattura> filterByAnno(@Param("anno") int anno);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :start AND :end")
    List<Fattura> filterByTwoTotals(@Param("start") double start, @Param("end") double end);
}
