package epic_energy_services.bw2.repositories;

import epic_energy_services.bw2.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query("SELECT u FROM Cliente u WHERE u.dataUltimoContatto >= ?1 AND u.dataUltimoContatto < ?2 ORDER BY t.status DESC ")
    Cliente findByFirstName(LocalDate dataUltimoContatto);


    //FATTURATO
    @Query("SELECT u FROM Cliente u WHERE u.fatturatoAnnuale >= :fatturatoAnnuale ")
    Cliente findByfatturatoAnnuale(Double fatturatoAnnuale);

    @Query("SELECT u FROM Cliente u WHERE u.dataInserimento >= :dataInserimento ")
    Cliente findByDataInserimento(LocalDate dataInserimento);
}
