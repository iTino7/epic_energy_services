package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con questo id " + id + " non trovato"));
    }

//    public Cliente findByEmail(String email) {
//        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
//    }

    public Cliente createClient(NewClienteDTO payload) {

        if (clienteRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("Email già presente: " + payload.email());
        }

        Cliente cliente = new Cliente(
                payload.ragioneSociale(),
                payload.partitaIva(),
                payload.email(),
                LocalDate.now(),
                payload.dataUltimoContatto(),
                payload.fatturatoAnnuale(),
                payload.pec(),
                payload.telefono(),
                payload.emailContatto(),
                payload.nomeContatto(),
                payload.cognomeContatto(),
                payload.telefonoContatto(),
                "https://ui-avatars.com/api/?name=" + payload.nomeContatto() + "+" + payload.cognomeContatto()
        );
        return this.clienteRepository.save(cliente);
    }

    public Cliente updateClient(Long id, NewClienteDTO update) {
        Cliente cliente = this.findById(id);
        cliente.setRagioneSociale(update.ragioneSociale());
        cliente.setPartitaIva(update.partitaIva());
        cliente.setEmail(update.email());
        cliente.setDataUltimoContratto(update.dataUltimoContatto());
        cliente.setFatturatoAnnuale(update.fatturatoAnnuale());
        cliente.setPec(update.pec());
        cliente.setTelefono(update.telefono());
        cliente.setEmailContatto(update.emailContatto());
        cliente.setCognomeContatto(update.cognomeContatto());
        cliente.setTelefonoContatto(update.telefonoContatto());

        return this.clienteRepository.save(cliente);
    }

    public Page<Cliente> getClient(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.clienteRepository.findAll(pageable);
    }

    public void deleteClient(Long id) {
        Cliente found = this.findById(id);
        this.clienteRepository.delete(found);
    }

    public Cliente findByName(String nomeContatto) {
        return this.clienteRepository.findByFirstName(nomeContatto);
    }


}
