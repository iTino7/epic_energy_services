package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con questo id " + id + " non trovato"));
    }

    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public Cliente createClient(NewClienteDTO payload) {
        Cliente cliente = new Cliente(payload.getRagioneSociale(),
                payload.getPartitaIva(),
                payload.getEmail(),
                payload.getDataInserimento(),
                payload.getDataUltimoContratto(),
                payload.getFatturatoAnnuale(),
                payload.getPec(),
                payload.getTelefono(),
                payload.getEmailContatto(),
                payload.getCognomeContatto(),
                payload.getEmailContatto(),
                payload.getTelefonoContatto(),
                payload.getLogoAziendale()
        );
        return clienteRepository.save(cliente);
    }

    public Cliente updateClient(Long id, NewClienteDTO update) {
        Cliente cliente = this.findById(id);
        cliente.setRagioneSociale(update.getRagioneSociale());
        cliente.setPartitaIva(update.getPartitaIva());
        cliente.setEmail(update.getEmail());
        cliente.setDataInserimento(update.getDataInserimento());
        cliente.setDataUltimoContratto(update.getDataUltimoContratto());
        cliente.setFatturatoAnnuale(update.getFatturatoAnnuale());
        cliente.setPec(update.getPec());
        cliente.setTelefono(update.getTelefono());
        cliente.setEmailContatto(update.getEmailContatto());
        cliente.setCognomeContatto(update.getCognomeContatto());
        cliente.setTelefonoContatto(update.getTelefonoContatto());

        return clienteRepository.save(cliente);
    }

    public Page<Cliente> getClient(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return clienteRepository.findAll(pageable);
    }

    public void deleteClient(Long id) {
        Cliente found = this.findById(id);
        this.clienteRepository.delete(found);
    }


}
