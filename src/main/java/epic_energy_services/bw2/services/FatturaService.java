package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.*;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewFatturaDTO;
import epic_energy_services.bw2.repositories.FatturaRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private StatoFatturaService statoFatturaService;

    @Autowired
    private ClienteService clienteService;



    public Fattura findById(long id){
        return this.fatturaRepository.findById(id).orElseThrow(()-> new NotFoundException("Fattura con id " + id + " non trovata."));
    }

    public Fattura save(NewFatturaDTO payload){
        if (this.fatturaRepository.findByNumero(payload.numero()).isPresent()) throw new BadRequestException("Numero fattura " + payload.numero() + " già esistente.");
        Cliente found = this.clienteService.findById(payload.clienteId());
        StatoFattura stato = this.statoFatturaService.checkStato(payload.statoFattura());

        Fattura newFattura = new Fattura(payload.date(), payload.importo(), payload.numero(), stato, found);
        this.fatturaRepository.save(newFattura);
        return newFattura;
    }

    public Fattura findByIdAndUpdate(NewFatturaDTO payload, long id){
        if (this.fatturaRepository.findByNumero(payload.numero()).isPresent()) throw new BadRequestException("Numero fattura " + payload.numero() + " già esistente.");
        Fattura found = this.findById(id);
        Cliente cliente = this.clienteService.findById(payload.clienteId());
        StatoFattura stato = this.statoFatturaService.checkStato(payload.statoFattura());
        found.setData(payload.date());
        found.setImporto(payload.importo());
        found.setNumero(payload.numero());
        found.setStatoFattura(stato);
        found.setCliente(cliente);

        return this.fatturaRepository.save(found);
    }

    public void findByIdAndDelete(long id){
        Fattura found = this.findById(id);
        this.fatturaRepository.delete(found);
    }

    public List<Fattura> findFattureByCliente(long clienteId){
        Cliente found = this.clienteService.findById(clienteId);
        return this.fatturaRepository.findByCliente(found).orElseThrow(()-> new NotFoundException("Nessuna fattura trovata intestata a questo cliente."));
    }

    //https://medium.com/@AlexanderObregon/search-filters-in-spring-boot-apis-without-complex-query-builders-dcb69a0453c9
    public Page<Fattura> searchFattura(int pageNum, int pageSize, String sortBy, String stato, LocalDate data, Integer anno, Double start, Double end, Long clienteId){
        Specification<Fattura> spec = Specification.allOf(((root, query, cb) -> cb.conjunction()));
        if (pageSize > 50) pageSize = 50;

        if (stato != null){
            spec = spec.and((root, query, cb) -> {
                Join<StatoFattura, Fattura> fatturaJoin = root.join("statoFattura");
                return cb.equal(fatturaJoin.get("stato"), stato.toUpperCase());
            });
        }

        if (data != null){
            spec = spec.and((root, query, cb) -> cb.equal(root.get("data"), data));
        }

        if (anno != null){
            LocalDate startDate = LocalDate.of(anno, 1,1);
            LocalDate endDate = LocalDate.of(anno, 12,31);
            spec = spec.and((root, query, cb) -> cb.between(root.get("data"), startDate, endDate));
        }

        if (start != null && end != null){
            spec = spec.and((root, query, cb) -> cb.between(root.get("importo"), start, end));
        }

        if (clienteId != null){
            Cliente found = this.clienteService.findById(clienteId);
            spec = spec.and((root, query, cb) -> {
                Join<Fattura, Cliente> fatturaClienteJoin = root.join("cliente");
                return cb.equal(fatturaClienteJoin.get("id"), clienteId);
            });
        }

        Pageable pageable;

        if (sortBy.equalsIgnoreCase("provincia")){
            pageable = PageRequest.of(pageNum,pageSize);
            spec = spec.and((root, query, cb) -> {
                Join<Fattura, Cliente> fatturaClienteJoin = root.join("cliente");
                Join<Cliente, IndirizzoSedeLegale> clienteSedeLegaleJoin = fatturaClienteJoin.join("sedeLegale");
                Join<IndirizzoSedeLegale, Comune> sedeLegaleComuneJoin = clienteSedeLegaleJoin.join("comune");
                Join<Comune, Provincia> comuneProvinciaJoin = sedeLegaleComuneJoin.join("provincia");

                query.orderBy(cb.asc(comuneProvinciaJoin.get("provincia")));

                return cb.conjunction();
            });
        } else {
            pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        }
        return this.fatturaRepository.findAll(spec,pageable);
    }
}
