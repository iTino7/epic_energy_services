package epic_energy_services.bw2.controllers;


import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Cliente> getAllClient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "id") String sort_by) {
        return this.clienteService.getClient(page, size, sort_by);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createClient(@RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String errorMessages = validation.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((message1, message2) -> message1 + "," + message2)
                    .orElse("Errore di validazione");
            throw new ValidationException(errorMessages);
        }
        return clienteService.createClient(body);
    }

    @PutMapping("/{id}")
    public Cliente updateClient(@PathVariable long id, @RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String errorMessages = validation.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((message1, message2) -> message1 + "," + message2)
                    .orElse("Errore di validazione");
            throw new ValidationException(errorMessages);
        }
        return clienteService.updateClient(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable long id) {
        clienteService.deleteClient(id);
    }
}
