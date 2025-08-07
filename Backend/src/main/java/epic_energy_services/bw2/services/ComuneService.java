package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public Page<Comune> findAll (int pageNum, int pageSize, String sortBy){
        if (pageSize > 30) pageSize = 30;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return this.comuneRepository.findAll(pageable);
    }

    public Comune findById(long id){
        return this.comuneRepository.findById(id).orElseThrow(()-> new NotFoundException("Comune con id " + id + " non trovato."));
    }
}
