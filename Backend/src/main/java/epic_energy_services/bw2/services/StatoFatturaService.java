package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.StatoFattura;
import epic_energy_services.bw2.entities.StatoFatturaTypes;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatoFatturaService {

    @Autowired
    private StatoFatturaRepository repository;

    public StatoFattura checkStato(String str){
        switch (str.toUpperCase()){
            case "IN_ELABORAZIONE":
                return this.repository.findByStato(StatoFatturaTypes.IN_ELABORAZIONE);
            case "DA_PAGARE":
                return this.repository.findByStato(StatoFatturaTypes.DA_PAGARE);
            case "INSERITA":
                return this.repository.findByStato(StatoFatturaTypes.INSERITA);
            case "PAGATA":
                return this.repository.findByStato(StatoFatturaTypes.PAGATA);
            default:
                throw new BadRequestException("Stato fattura non valido.");
        }
    }
}
