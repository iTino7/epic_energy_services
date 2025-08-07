package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewIndirizzoDTO;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.repositories.IndirizzoSedeLegaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoSedeLegaleService {

    @Autowired
    private IndirizzoSedeLegaleRepository indirizzoRepository;
    @Autowired
    private ComuneService comuneService;

    public List<IndirizzoSedeLegale> findAll() {
        return indirizzoRepository.findAll();
    }

    public IndirizzoSedeLegale findById(Long id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Sede legale con id " + id + " non trovata."));
    }

    public void findByViaAndCivicoAndLocalita(String via, String civico, String localita) {
        indirizzoRepository.findByViaAndCivicoAndLocalita(via, civico, localita).ifPresent(indirizzo  -> { throw new BadRequestException("Indirizzo in " + via  + " al civico " + civico + " esiste già.");});
    }

    public IndirizzoSedeLegale crea(NewIndirizzoDTO dto) {
        this.findByViaAndCivicoAndLocalita(dto.via(), dto.civico(), dto.localita());
        Comune comune = comuneService.findById(dto.comuneId());

        IndirizzoSedeLegale indirizzo = new IndirizzoSedeLegale(
                dto.via(), dto.civico(), dto.localita(), dto.cap(), comune
        );
        return indirizzoRepository.save(indirizzo);
    }


    public List<IndirizzoSedeLegale> findByCap(String cap) {
        return indirizzoRepository.findByCap(cap);
    }

    public Optional<IndirizzoSedeLegale> findByNomeComune(String denominazione) {
        return indirizzoRepository.findByComune_Denominazione(denominazione);
    }

    public IndirizzoSedeLegale update(Long id, NewIndirizzoDTO payload) {
        //Trovo ID
        IndirizzoSedeLegale found = this.findById(id);

        //Cerco Via e Civico
        this.findByViaAndCivicoAndLocalita(payload.via(), payload.civico(), payload.localita());

        Comune comune = comuneService.findById(payload.comuneId());

        found.setVia(payload.via());
        found.setCivico(payload.civico());
        found.setLocalita(payload.localita());
        found.setCap(payload.cap());
        found.setComune(comune);


        return indirizzoRepository.save(found);
    }



    public void delete(Long id) {
        indirizzoRepository.deleteById(id);
    }


}
