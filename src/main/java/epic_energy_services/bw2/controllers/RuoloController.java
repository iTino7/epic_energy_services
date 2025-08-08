package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Ruolo;
import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewRuoloDTO;
import epic_energy_services.bw2.payloads.UserRespDTO;
import epic_energy_services.bw2.services.RuoloService;
import epic_energy_services.bw2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @Autowired
    private UserService userService;

    //Creare un Ruolo
    @PostMapping
    public Ruolo assignRole(@RequestBody @Validated NewRuoloDTO newRuoloDTO, BindingResult validation) {
        if (validation.hasErrors()){
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return ruoloService.creaRuolo(newRuoloDTO);
    }

    //Assegnare un ruolo
    @PutMapping("/assign")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserRespDTO assegnaRuolo(@RequestParam Long userId, @RequestParam String nomeRuolo) {
        userService.assegnaRuoloAUser(userId, nomeRuolo);
        return new UserRespDTO(userId);
    }

    
}
