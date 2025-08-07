package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
//    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);


}
