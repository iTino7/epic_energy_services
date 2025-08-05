package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.services.IndirizzoSedeLegaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    private final IndirizzoSedeLegaleService indirizzoService;

    @Autowired
    public IndirizzoController(IndirizzoSedeLegaleService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @GetMapping
    public List<IndirizzoSedeLegale> getAllIndirizzi() {
        return indirizzoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndirizzoSedeLegale> getIndirizzoById(@PathVariable Long id) {
        return indirizzoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IndirizzoSedeLegale> CreateIndirizzo(@RequestBody IndirizzoSedeLegale indirizzo) {
        IndirizzoSedeLegale created = indirizzoService.save(indirizzo);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndirizzoSedeLegale> updateIndirizzo(@PathVariable Long id, @RequestBody IndirizzoSedeLegale indirizzo) {
        try {
            IndirizzoSedeLegale update = indirizzoService.update(id, indirizzo);
            return ResponseEntity.ok(update);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
