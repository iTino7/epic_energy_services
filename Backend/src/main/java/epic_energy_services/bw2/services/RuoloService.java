package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Ruolo;
import epic_energy_services.bw2.payloads.NewRuoloDTO;
import epic_energy_services.bw2.repositories.RuoloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    public Ruolo findByNome(String nomeRuolo) {
        return ruoloRepository.findByNomeRuolo(nomeRuolo.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + nomeRuolo));
    }

    public Ruolo creaRuolo(NewRuoloDTO dto) {
        Optional<Ruolo> esistente = ruoloRepository.findByNomeRuolo(dto.nomeRuolo().toUpperCase());
        if (esistente.isPresent()) {
            throw new RuntimeException("Il ruolo '" + dto.nomeRuolo() + "' esiste già");
        }

        Ruolo ruolo = new Ruolo(dto.nomeRuolo().toUpperCase());
        return ruoloRepository.save(ruolo);
    }
}
