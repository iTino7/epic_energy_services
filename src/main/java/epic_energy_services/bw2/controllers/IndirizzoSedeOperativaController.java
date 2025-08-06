package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.services.IndirizzoSedeOperativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/indirizzi/sede-operativa")
public class IndirizzoSedeOperativaController {

    @Autowired
    private IndirizzoSedeOperativaService service;

    @Autowired
    private ComuneRepository comuneRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndirizzoSedeOperativa crea(@RequestBody @Valid NewIndirizzoDTO dto) {
        return service.creaNuovaSedeOperativa(dto);
    }

    @GetMapping
    public List<IndirizzoSedeOperativa> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IndirizzoSedeOperativa findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indirizzo non trovato"));
    }

    @PutMapping("/{id}")
    public IndirizzoSedeOperativa update(@PathVariable Long id, @RequestBody @Valid IndirizzoSedeOperativa dettagli) {
        return service.update(id, dettagli);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search/by-cap")
    public List<IndirizzoSedeOperativa> findByCap(@RequestParam String cap) {
        return service.findByCap(cap);
    }

    @GetMapping("/search/by-comune")
    public IndirizzoSedeOperativa findByComuneDenominazione(@RequestParam String denominazione) {
        return service.findByComuneDenominazione(denominazione)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indirizzo non trovato per il comune specificato"));
    }

    @GetMapping("/search/by-via-civico")
    public IndirizzoSedeOperativa findByViaAndCivico(@RequestParam String via, @RequestParam String civico) {
        return service.findByViaAndCivico(via, civico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indirizzo non trovato per via e civico specificati"));
    }
}


