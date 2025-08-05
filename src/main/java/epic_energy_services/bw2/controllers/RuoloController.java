package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Ruolo;
import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.payloads.NewRuoloDTO;
import epic_energy_services.bw2.services.RuoloService;
import epic_energy_services.bw2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Ruolo assignRole(@RequestBody @Valid NewRuoloDTO newRuoloDTO) {
        return ruoloService.creaRuolo(newRuoloDTO);
    }

    @PutMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public String assegnaRuolo(@RequestParam Long userId, @RequestParam String nomeRuolo) {
        userService.assegnaRuoloAUser(userId, nomeRuolo);
        return "Ruolo '" + nomeRuolo + "' assegnato all'utente con ID " + userId;
    }


}
