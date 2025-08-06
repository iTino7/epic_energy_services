package epic_energy_services.bw2.controllers;


import epic_energy_services.bw2.entities.Fattura;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewFatturaDTO;
import epic_energy_services.bw2.payloads.NewFatturaRespDTO;
import epic_energy_services.bw2.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> findAll(@RequestParam(name = "page",defaultValue = "0") int page,
                                       @RequestParam(name = "size",defaultValue = "30") int size,
                                       @RequestParam(name = "sortBy",defaultValue = "id") String sortBy) {
        return this.fatturaService.findAllFatture(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewFatturaRespDTO createFattura (@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResults){
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewFatturaRespDTO(newFattura.getId());
        }
    }

    @PutMapping("/{fatturaId}")
    public NewFatturaRespDTO findByIdAndUpdate(@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResults,@PathVariable("fatturaId") long fatturaId){
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Fattura updatedFattura = this.fatturaService.findByIdAndUpdate(payload, fatturaId);
            return new NewFatturaRespDTO(updatedFattura.getId());
        }
    }

    @DeleteMapping("/{fatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable long fatturaId){
        this.fatturaService.findByIdAndDelete(fatturaId);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Fattura> findAllFattureByCliente(@PathVariable("clienteId") long clienteId){
        return this.fatturaService.findFattureByCliente(clienteId);
    }
}
