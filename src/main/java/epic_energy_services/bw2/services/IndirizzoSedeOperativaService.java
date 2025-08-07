package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.IndirizzoSedeOperativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoSedeOperativaService {

    @Autowired
    private IndirizzoSedeOperativaRepository repository;

    @Autowired
    private ComuneService comuneService;

    public List<IndirizzoSedeOperativa> findAll() {
        return repository.findAll();
    }

    public IndirizzoSedeOperativa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sede Operativa non trovata"));
    }

    public IndirizzoSedeOperativa save(NewIndirizzoDTO dto) {
        Comune comune = comuneService.findById(dto.comuneId());

        IndirizzoSedeOperativa indirizzo = new IndirizzoSedeOperativa(
                dto.via(), dto.civico(), dto.località(), dto.cap(), comune
        );
        return repository.save(indirizzo);
    }

    public void findByViaAndCivico(String via, String civico) {
        repository.findByViaAndCivico(via, civico).ifPresent(indirizzo -> {
            throw new BadRequestException("Indirizzo in " + via + " al civico " + civico + " esiste già");
        });
    }

    public IndirizzoSedeOperativa update(Long id, NewIndirizzoDTO newIndirizzoDTO) {
        IndirizzoSedeOperativa found = this.findById(id);

        this.findByViaAndCivico(newIndirizzoDTO.via(), newIndirizzoDTO.civico());

        Comune comune = comuneService.findById(newIndirizzoDTO.comuneId());

        found.setVia(newIndirizzoDTO.via());
        found.setCivico(newIndirizzoDTO.civico());
        found.setLocalità(newIndirizzoDTO.località());
        found.setCap(newIndirizzoDTO.cap());
        found.setComune(comune);

        return repository.save(found);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public IndirizzoSedeOperativa creaNuovaSedeOperativa(NewIndirizzoDTO dto) {
        Comune comune = comuneService.findById(dto.comuneId());

        boolean esiste = repository.findByViaAndCivico(dto.via(), dto.civico()).isPresent();
        if (esiste) {
            throw new IllegalArgumentException("L'indirizzo della sede operativa già esiste");
        }

        IndirizzoSedeOperativa nuova = new IndirizzoSedeOperativa(
                dto.via(), dto.civico(), dto.località(), dto.cap(), comune
        );

        return repository.save(nuova);
    }

    public List<IndirizzoSedeOperativa> findByCap(String cap) {
        return repository.findByCap(cap);
    }

    public Optional<IndirizzoSedeOperativa> findByComuneDenominazione(String denominazione) {
        return repository.findByComune_Denominazione(denominazione);
    }

}

