package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    Optional<Fattura> findByNumero(int numero);
}
