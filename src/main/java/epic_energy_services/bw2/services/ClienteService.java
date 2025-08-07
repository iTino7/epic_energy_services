package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.entities.IndirizzoSedeOperativa;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.repositories.ClienteRepository;
import epic_energy_services.bw2.repositories.IndirizzoSedeLegaleRepository;
import epic_energy_services.bw2.repositories.IndirizzoSedeOperativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.net.IDN;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IndirizzoSedeLegaleRepository sedeLegaleRepository;

    @Autowired
    private IndirizzoSedeOperativaRepository sedeOperativaRepository;

    @Autowired
    private IndirizzoSedeLegaleService sedeLegaleService;

    @Autowired
    private IndirizzoSedeOperativaService sedeOperativaService;

    @Autowired
    private ComuneService comuneService;

    public Cliente findById(long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con questo id " + id + " non trovato"));
    }

    public void findByEmail(String email) {
         this.clienteRepository.findByEmail(email).ifPresent(cliente -> {
             throw new BadRequestException("Email " + email + " già in uso.");
         });
    }

    public Cliente createClient(NewClienteDTO payload) {

        this.sedeLegaleService.findByViaAndCivicoAndLocalita(payload.viaSL(), payload.civicoSL(), payload.localitaSL());
        this.sedeOperativaService.findByViaAndCivicoAndLocalita(payload.viaSO(), payload.civicoSO(), payload.localitaSO());
        Comune comuneSL = comuneService.findById(payload.comuneIdSL());
        Comune comuneSO = comuneService.findById(payload.comuneIdSO());

        IndirizzoSedeLegale indirizzoSL = new IndirizzoSedeLegale(payload.viaSL(),payload.civicoSL(),payload.localitaSL(),payload.capSL(), comuneSL);
        IndirizzoSedeLegale newSL =  this.sedeLegaleRepository.save(indirizzoSL);


        this.findByEmail(payload.email());

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
                newSL
        );
        Cliente newCliente = this.clienteRepository.save(cliente);

        System.out.println("Località da salvare: " + payload.localitaSO());


        IndirizzoSedeOperativa indirizzoSO = new IndirizzoSedeOperativa(payload.viaSO(),payload.civicoSO(),payload.localitaSO(),payload.capSO(), comuneSO, newCliente);
        IndirizzoSedeOperativa newSO = this.sedeOperativaRepository.save(indirizzoSO);

        System.out.println("Dopo save, localita = " + newSO.getLocalita());


        return newCliente;
    }

    public Cliente updateClient(Long id, NewClienteDTO update) {
        Cliente cliente = this.findById(id);
        cliente.setRagioneSociale(update.ragioneSociale());
        cliente.setPartitaIva(update.partitaIva());
        cliente.setEmail(update.email());
        cliente.setDataUltimoContatto(update.dataUltimoContatto());
        cliente.setFatturatoAnnuale(update.fatturatoAnnuale());
        cliente.setPec(update.pec());
        cliente.setTelefono(update.telefono());
        cliente.setEmailContatto(update.emailContatto());
        cliente.setCognomeContatto(update.cognomeContatto());
        cliente.setTelefonoContatto(update.telefonoContatto());

        return this.clienteRepository.save(cliente);
    }

    public void deleteClient(Long id) {
        Cliente found = this.findById(id);
        this.clienteRepository.delete(found);
    }

    public Page<Cliente> searchCliente(int pageNum, int pageSize, String sortBy, Double fatturatoAnnuale, LocalDate dataInserimento, LocalDate ultimoContatto, String partialName) {
        Specification<Cliente> spec = Specification.allOf((root, query, cb) -> cb.conjunction());

        if (pageSize > 20) pageSize = 20;

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));


        if (fatturatoAnnuale != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("fatturatoAnnuale"), fatturatoAnnuale));
        }

        if (dataInserimento != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataInserimento"), dataInserimento));
        }

        if (ultimoContatto != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataUltimoContratto"), ultimoContatto));
        }

        if (partialName != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nomeContatto")), "%" + partialName.toLowerCase() + "%"));
        }

        return this.clienteRepository.findAll(spec, pageable);

    }


}
