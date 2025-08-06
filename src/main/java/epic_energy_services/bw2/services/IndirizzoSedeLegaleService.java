package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.IndirizzoSedeLegaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoSedeLegaleService {
    private final IndirizzoSedeLegaleRepository indirizzoRepository;

    @Autowired
    public IndirizzoSedeLegaleService(IndirizzoSedeLegaleRepository indirizzoRepository) {
        this.indirizzoRepository = indirizzoRepository;
    }

    public List<IndirizzoSedeLegale> findAll() {
        return indirizzoRepository.findAll();
    }

    public Optional<IndirizzoSedeLegale> findById(Long id) {
        return indirizzoRepository.findById(id);
    }

    public IndirizzoSedeLegale save(IndirizzoSedeLegale indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    public List<IndirizzoSedeLegale> findByCap(String cap) {
        return indirizzoRepository.findByCap(cap);
    }

    public List<IndirizzoSedeLegale> findByNomeComune(String nomeComune) {
        return indirizzoRepository.findByNomeComune(nomeComune);
    }

    public Optional<IndirizzoSedeLegale> findByViaAndCivico(String via, String civico) {
        return indirizzoRepository.findByViaAndCivico(via, civico);
    }

    public IndirizzoSedeLegale update(Long id, IndirizzoSedeLegale indirizzoDetails) {
        return indirizzoRepository.findById(id)
                .map(indirizzo -> {
                    indirizzo.setVia(indirizzoDetails.getVia());
                    indirizzo.setCivico(indirizzoDetails.getCivico());
                    indirizzo.setLocalità(indirizzoDetails.getLocalità());
                    indirizzo.setCap(indirizzoDetails.getCap());
                    indirizzo.setComune(indirizzoDetails.getComune());
                    return indirizzoRepository.save(indirizzo);
                }).orElseThrow(() -> new RuntimeException("indirizzo con sede legale non trovato con id: " + id));
    }

    public void delete(Long id) {
        indirizzoRepository.deleteById(id);
    }


}
