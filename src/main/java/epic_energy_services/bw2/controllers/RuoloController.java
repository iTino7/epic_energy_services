package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Ruolo;
import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.payloads.NewRuoloDTO;
import epic_energy_services.bw2.services.RuoloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @PostMapping
    public Ruolo assignRole(@RequestBody @Valid NewRuoloDTO newRuoloDTO) {
        return ruoloService.creaRuolo(newRuoloDTO);
    }
}
