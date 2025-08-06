package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Cliente u WHERE LOWER(u.nomeContatto) LIKE LOWER(CONCAT('%', :nomeContatto, '%'))")
    Cliente findByFirstName(String nomeContatto);
}
