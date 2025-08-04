package epic_energy_services.bw2.repository;

import epic_energy_services.bw2.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    Provincia findByProvincia(String provincia);
}
