package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.Fattura;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewFatturaDTO;
import epic_energy_services.bw2.payloads.NewFatturaRespDTO;
import epic_energy_services.bw2.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> findAllComuni(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "30") int size,
                                       @RequestParam(defaultValue = "denominazione") String sortBy) {
        return this.fatturaService.findAllFatture(page, size, sortBy);
    }

    @PostMapping
    public NewFatturaRespDTO createFattura (@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResults){
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewFatturaRespDTO(newFattura.getId());
        }
    }
}
