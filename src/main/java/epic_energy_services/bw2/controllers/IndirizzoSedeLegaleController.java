package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.services.IndirizzoSedeLegaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sedi-legali")
public class IndirizzoSedeLegaleController {

    @Autowired
    private IndirizzoSedeLegaleService service;

    @GetMapping
    public List<IndirizzoSedeLegale> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IndirizzoSedeLegale findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indirizzo non trovato con id: " + id));
    }


    @GetMapping("/cap")
    public List<IndirizzoSedeLegale> findByCap(@RequestParam String cap) {
        return service.findByCap(cap);
    }

    @GetMapping("/search")
    public IndirizzoSedeLegale findByViaAndCivico(@RequestParam String via, @RequestParam String civico) {
        return service.findByViaAndCivico(via, civico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "indirizzo non trovato"));
    }

    @PutMapping("/{id}")
    public IndirizzoSedeLegale update(@PathVariable Long id, @RequestBody @Valid IndirizzoSedeLegale indirizzoDetails) {
        return service.update(id, indirizzoDetails);
    }


}
