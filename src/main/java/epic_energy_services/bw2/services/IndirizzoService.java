package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Indirizzo;
import epic_energy_services.bw2.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoService {
    private final IndirizzoRepository indirizzoRepository;

    @Autowired
    public IndirizzoService(IndirizzoRepository indirizzoRepository) {
        this.indirizzoRepository = indirizzoRepository;
    }

    public List<Indirizzo> findAll() {
        return indirizzoRepository.findAll();
    }

    public Optional<Indirizzo> findById(Long id) {
        return indirizzoRepository.findById(id);
    }

    public Indirizzo save(Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    public Indirizzo update(Long id, Indirizzo indirizzoDetails) {
        return indirizzoRepository.findById(id)
                .map(indirizzo -> {
                    indirizzo.setVia(indirizzoDetails.getVia());
                    indirizzo.setCivico(indirizzoDetails.getCivico());
                    indirizzo.setLocalità(indirizzoDetails.getLocalità());
                    indirizzo.setCap(indirizzoDetails.getCap());
                    indirizzo.setComune(indirizzoDetails.getComune());
                    return indirizzoRepository.save(indirizzo);
                }).orElseThrow(() -> new RuntimeException("indirizzo non trovato con id: " + id));
    }

    public void delete(Long id) {
        indirizzoRepository.deleteById(id);
    }


}
