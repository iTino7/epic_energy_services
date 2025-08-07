package epic_energy_services.bw2.controllers;


import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Page<Cliente> getAllClient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) Double fatturatoAnnuale,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate ultimoContatto,
            @RequestParam(required = false) String partialName) {
        return this.clienteService.searchCliente(page, size, sortBy, fatturatoAnnuale, dataInserimento, ultimoContatto, partialName);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Cliente findById(@PathVariable long id) {
        return this.clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Cliente createClient(@RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            return this.clienteService.createClient(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateClient(@PathVariable long id, @RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String errorMessages = validation.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((message1, message2) -> message1 + "," + message2)
                    .orElse("Errore di validazione");
            throw new ValidationException(errorMessages);
        }
        return this.clienteService.updateClient(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteClient(@PathVariable long id) {
        this.clienteService.deleteClient(id);
    }
}
