package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import epic_energy_services.bw2.exception.BadRequestException;
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
        return indirizzoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("sede legale non trovata"));
    }

    public IndirizzoSedeLegale crea(NewIndirizzoDTO dto) {
        Comune comune = comuneService.findById(dto.comuneId());

        IndirizzoSedeLegale indirizzo = new IndirizzoSedeLegale(
                dto.via(), dto.civico(), dto.località(), dto.cap(), comune
        );
        return indirizzoRepository.save(indirizzo);
    }


    public List<IndirizzoSedeLegale> findByCap(String cap) {
        return indirizzoRepository.findByCap(cap);
    }

    public Optional<IndirizzoSedeLegale> findByNomeComune(String denominazione) {
        return indirizzoRepository.findByComune_Denominazione(denominazione);
    }

    public void findByViaAndCivico(String via, String civico) {
        indirizzoRepository.findByViaAndCivico(via, civico).ifPresent(Indirizzo  -> { throw new BadRequestException("Indirizzo in " +via  + "al civico " +civico + "esiste già ");});
    }

    public IndirizzoSedeLegale update(Long id, NewIndirizzoDTO newIndirizzoDTO) {
        //Trovo ID
        IndirizzoSedeLegale found = this.findById(id);

        //Cerco Via e Civico
        this.findByViaAndCivico(newIndirizzoDTO.via(), newIndirizzoDTO.civico());

        Comune comune = comuneService.findById(newIndirizzoDTO.comuneId());

        found.setVia(newIndirizzoDTO.via());
        found.setCivico(newIndirizzoDTO.civico());
        found.setLocalità(newIndirizzoDTO.località());
        found.setCap(newIndirizzoDTO.cap());
        found.setComune(comune);


        return indirizzoRepository.save(found);
    }



    public void delete(Long id) {
        indirizzoRepository.deleteById(id);
    }


}
