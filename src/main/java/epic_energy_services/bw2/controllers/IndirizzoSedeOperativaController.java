package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewIDRespDTO;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.services.IndirizzoSedeOperativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//VERIFICATO

@RestController
@RequestMapping("/indirizzi/sede-operativa")
public class IndirizzoSedeOperativaController {

    @Autowired
    private IndirizzoSedeOperativaService service;

    @Autowired
    private ComuneRepository comuneRepository;

//    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public NewIDRespDTO crea(@RequestBody @Validated NewIndirizzoDTO dto, BindingResult validation) {
//        if (validation.hasErrors()) {
//            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
//        } else {
//            IndirizzoSedeOperativa newCreated = service.creaNuovaSedeOperativa(dto);
//            return new NewIDRespDTO(newCreated.getId());
//        }
//    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<IndirizzoSedeOperativa> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public IndirizzoSedeOperativa findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewIDRespDTO update(@PathVariable Long id, @RequestBody @Validated NewIndirizzoDTO newIndirizzoDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            IndirizzoSedeOperativa updated = service.update(id, newIndirizzoDTO);
            return new NewIDRespDTO(updated.getId());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}


