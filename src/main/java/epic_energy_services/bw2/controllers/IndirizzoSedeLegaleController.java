package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.services.IndirizzoSedeLegaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedi-legali")
public class IndirizzoSedeLegaleController {
    @Autowired
    private IndirizzoSedeLegaleService service;
    @Autowired
    private ComuneRepository comuneRepository;

    public IndirizzoSedeLegaleController(IndirizzoSedeLegaleService service, ComuneRepository comuneRepository) {
        this.service = service;
        this.comuneRepository = comuneRepository;
    }

    @GetMapping
    public List<IndirizzoSedeLegale> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IndirizzoSedeLegale getById(@PathVariable Long id) {
        return service.findById(id);
    }

}
