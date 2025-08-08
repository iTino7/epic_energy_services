package epic_energy_services.bw2.controllers;


import epic_energy_services.bw2.entities.Fattura;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewFatturaDTO;
import epic_energy_services.bw2.payloads.NewIDRespDTO;
import epic_energy_services.bw2.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Page<Fattura> searchFatture(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false)String statoFattura,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) Double start,
            @RequestParam(required = false) Double end,
            @RequestParam(required = false) Long clienteId
    ){
        return this.fatturaService.searchFattura(page, size, sortBy, statoFattura, data, anno, start, end, clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewIDRespDTO createFattura (@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResults){
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewIDRespDTO(newFattura.getId());
        }
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewIDRespDTO findByIdAndUpdate(@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResults, @PathVariable("fatturaId") long fatturaId){
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Fattura updatedFattura = this.fatturaService.findByIdAndUpdate(payload, fatturaId);
            return new NewIDRespDTO(updatedFattura.getId());
        }
    }

    @DeleteMapping("/{fatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFattura(@PathVariable("fatturaId") long fatturaId){
        this.fatturaService.findByIdAndDelete(fatturaId);
    }

}
