package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Indirizzo;
import epic_energy_services.bw2.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    private final IndirizzoService indirizzoService;

    @Autowired
    public IndirizzoController(IndirizzoService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @GetMapping
    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indirizzo> getIndirizzoById(@PathVariable Long id) {
        return indirizzoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Indirizzo> CreateIndirizzo(@RequestBody Indirizzo indirizzo) {
        Indirizzo created = indirizzoService.save(indirizzo);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indirizzo> updateIndirizzo(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
        try {
            Indirizzo update = indirizzoService.update(id, indirizzo);
            return ResponseEntity.ok(update);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
