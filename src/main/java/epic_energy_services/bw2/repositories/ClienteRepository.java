package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByPartitaIva(int partitaIva);

    Optional<Cliente> findByTelefono(String telefono);


    Optional<Cliente> findByPec(String pec);
}
