package epic_energy_services.bw2.controllers;


import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Cliente> getAllClient(Pageable pageable) {
        return clienteService.getClient(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createClient(@RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Error data");
        }
        return clienteService.createClient(body);
    }

    @PutMapping("/{id}")
    public Cliente updateClient(@PathVariable long id, @RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Error data!");
        }
        return clienteService.updateClient(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable long id) {
        clienteService.deleteClient(id);
    }
}
