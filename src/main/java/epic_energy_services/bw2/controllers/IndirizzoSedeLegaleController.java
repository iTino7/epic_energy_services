package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewIDRespDTO;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.services.IndirizzoSedeLegaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedi-legali")
public class IndirizzoSedeLegaleController {

    @Autowired
    private IndirizzoSedeLegaleService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<IndirizzoSedeLegale> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public IndirizzoSedeLegale getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @PreAuthorize(("hasAuthority('ADMIN')"))
    public NewIDRespDTO create(@RequestBody @Validated NewIndirizzoDTO payload, BindingResult validation){
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            IndirizzoSedeLegale newIndirizzo = this.service.crea(payload);
            return new NewIDRespDTO(newIndirizzo.getId());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewIDRespDTO update(@PathVariable Long id, @RequestBody @Validated NewIndirizzoDTO newIndirizzoDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            IndirizzoSedeLegale updatedIndirizzo = service.update(id, newIndirizzoDTO);
            return new NewIDRespDTO(updatedIndirizzo.getId());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSede(@PathVariable Long id) {
        service.delete(id);
    }
}
