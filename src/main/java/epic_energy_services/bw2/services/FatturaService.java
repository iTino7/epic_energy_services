package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Cliente;
import epic_energy_services.bw2.entities.Fattura;
import epic_energy_services.bw2.entities.StatoFattura;
import epic_energy_services.bw2.entities.StatoFatturaTypes;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewFatturaDTO;
import epic_energy_services.bw2.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private StatoFatturaService statoFatturaService;

    @Autowired
    private ClienteService clienteService;

    public Page<Fattura> findAllFatture(int pageNum, int pageSize, String sortBy){
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        return this.fatturaRepository.findAll(pageable);
    }

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













}
