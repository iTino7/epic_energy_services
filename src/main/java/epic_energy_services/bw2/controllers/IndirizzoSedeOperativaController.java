package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.services.IndirizzoSedeOperativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sedi-operative")
public class IndirizzoSedeOperativaController {
    @Autowired
    private IndirizzoSedeOperativaService service;
    @Autowired
    private ComuneRepository comuneRepository;

    public IndirizzoSedeOperativaController(IndirizzoSedeOperativaService service, ComuneRepository comuneRepository) {
        this.service = service;
        this.comuneRepository = comuneRepository;
    }

    @PostMapping
    public ResponseEntity<?> creaNuovaSedeOperativa(@RequestBody @Valid NewIndirizzoDTO dto) {
        Comune comune = comuneRepository.findById(dto.comuneId())
                .orElseThrow(() -> new IllegalArgumentException("comune non trovato"));

        IndirizzoSedeOperativa nuovo = service.creaNuovaSedeOperativa(dto, comune);
        return ResponseEntity.ok(nuovo);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
