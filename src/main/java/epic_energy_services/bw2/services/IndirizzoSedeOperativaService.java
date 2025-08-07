package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Cliente;
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

    @Autowired
    private ClienteService clienteService;

    public List<IndirizzoSedeOperativa> findAll() {
        return repository.findAll();
    }

    public IndirizzoSedeOperativa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sede Operativa non trovata"));
    }

    public void findByViaAndCivicoAndLocalita(String via, String civico, String localita) {
        this.repository.findByViaAndCivicoAndLocalita(via, civico, localita).ifPresent(indirizzo  -> { throw new BadRequestException("Indirizzo in " + via  + " al civico " + civico + " esiste già.");});
    }

    public IndirizzoSedeOperativa update(Long id, NewIndirizzoDTO payload) {
        IndirizzoSedeOperativa found = this.findById(id);

        this.findByViaAndCivicoAndLocalita(payload.via(), payload.civico(), payload.localita());

        Comune comune = comuneService.findById(payload.comuneId());

        found.setVia(payload.via());
        found.setCivico(payload.civico());
        found.setLocalita(payload.localita());
        found.setCap(payload.cap());
        found.setComune(comune);

        return repository.save(found);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public IndirizzoSedeOperativa creaNuovaSedeOperativa(NewIndirizzoDTO dto) {
        this.findByViaAndCivicoAndLocalita(dto.via(), dto.civico(), dto.localita());
        Comune comune = comuneService.findById(dto.comuneId());
        Cliente cliente = this.clienteService.findById(dto.clienteId());

        IndirizzoSedeOperativa nuova = new IndirizzoSedeOperativa(
                dto.via(), dto.civico(), dto.localita(), dto.cap(), comune, cliente
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

