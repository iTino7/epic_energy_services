package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.services.IndirizzoSedeLegaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sedi-legali")
public class IndirizzoSedeLegaleController {
    private final IndirizzoSedeLegaleService service;
    private final ComuneRepository comuneRepository;

    public IndirizzoSedeLegaleController(IndirizzoSedeLegaleService service, ComuneRepository comuneRepository) {
        this.service = service;
        this.comuneRepository = comuneRepository;
    }

    @PostMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
