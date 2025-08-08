package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.*;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewClienteDTO;
import epic_energy_services.bw2.repositories.ClienteRepository;
import epic_energy_services.bw2.repositories.IndirizzoSedeLegaleRepository;
import epic_energy_services.bw2.repositories.IndirizzoSedeOperativaRepository;
import epic_energy_services.bw2.tools.MailSender;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private MailSender mailSender;

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

        IndirizzoSedeOperativa indirizzoSO = new IndirizzoSedeOperativa(payload.viaSO(),payload.civicoSO(),payload.localitaSO(),payload.capSO(), comuneSO);
        IndirizzoSedeOperativa newSO = this.sedeOperativaRepository.save(indirizzoSO);

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
                newSL,
                newSO
        );
        Cliente newCliente = this.clienteRepository.save(cliente);

        System.out.println("Località da salvare: " + payload.localitaSO());




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

        if (fatturatoAnnuale != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("fatturatoAnnuale"), fatturatoAnnuale));
        }

        if (dataInserimento != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataInserimento"), dataInserimento));
        }

        if (ultimoContatto != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataUltimoContatto"), ultimoContatto));
        }

        if (partialName != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nomeContatto")), "%" + partialName.toLowerCase() + "%"));
        }
        Pageable pageable;

        if (sortBy.equalsIgnoreCase("provincia")){
            pageable = PageRequest.of(pageNum,pageSize);

            spec = spec.and((root, query, cb) -> {
                Join<Cliente, IndirizzoSedeLegale> clienteSedeLegaleJoin = root.join("sedeLegale");
                Join<IndirizzoSedeLegale, Comune> sedeLegaleComuneJoin = clienteSedeLegaleJoin.join("comune");
                Join<Comune, Provincia> comuneProvinciaJoin = sedeLegaleComuneJoin.join("provincia");

                query.orderBy(cb.asc(comuneProvinciaJoin.get("provincia")));

                return cb.conjunction();
            });
        } else {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        }

        return this.clienteRepository.findAll(spec, pageable);

    }

    public void sendEmailToCliente(long id, String type){
        if (type.equals("saldo")) mailSender.sendNotificationEmail(this.findById(id));
        else if (type.equals("natale")) mailSender.sendChristmasEmail(this.findById(id));
        else mailSender.sendThanksEmail(this.findById(id));
    }


}
