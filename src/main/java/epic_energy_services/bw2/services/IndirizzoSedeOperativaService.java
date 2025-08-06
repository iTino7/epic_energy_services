package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.IndirizzoSedeOperativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoSedeOperativaService {
    private final IndirizzoSedeOperativaRepository repository;
    private boolean indirizzoRepository;

    @Autowired
    public IndirizzoSedeOperativaService(IndirizzoSedeOperativaRepository repository) {
        this.repository = repository;
    }


    public List<IndirizzoSedeOperativa> findAll() {
        return repository.findAll();
    }

    public Optional<IndirizzoSedeOperativa> findById(Long id) {
        return repository.findById(id);
    }

    public IndirizzoSedeOperativa save(Long id, IndirizzoSedeOperativa indirizzo) {
        return repository.save(indirizzo);
    }

    public IndirizzoSedeOperativa update(Long id, IndirizzoSedeOperativa dettagli) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setVia(dettagli.getVia());
                    existing.setCivico(dettagli.getCivico());
                    existing.setLocalità(dettagli.getLocalità());
                    existing.setCap(dettagli.getCap());
                    existing.setComune(dettagli.getComune());
                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Indirizzo sede Operativa non trovato con ID: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public IndirizzoSedeOperativa creaNuovaSedeOperativa(NewIndirizzoDTO dto, Comune comune) {
        boolean esiste = repository.findByViaAndCivico(dto.via(), dto.civico()).isPresent();
        if (esiste) {
            throw new IllegalArgumentException("'L'indirizzo della sede operativa gia esiste");
        }
        IndirizzoSedeOperativa nuova = new IndirizzoSedeOperativa(
                dto.via(), dto.civico(), dto.località(), dto.cap(), comune
        );
        return repository.save(nuova);
    }
}
