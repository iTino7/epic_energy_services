package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Provincia;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Page<Provincia> findAll(int pageNum, int pageSize, String sortBy){
        if (pageSize > 30) pageSize = 30;
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        return this.provinciaRepository.findAll(pageable);
    }

    public Provincia findById (long id){
        return this.provinciaRepository.findById(id).orElseThrow(()-> new NotFoundException("Provincia con id " + id + " non trovata."));
    }
}
