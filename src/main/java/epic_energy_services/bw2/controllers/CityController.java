package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.Provincia;
import epic_energy_services.bw2.services.ComuneService;
import epic_energy_services.bw2.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ComuneService comuneService;

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/comuni")
    public Page<Comune> findAllComuni(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "30") int size,
                                @RequestParam(defaultValue = "denominazione") String sortBy){
        return this.comuneService.findAll(page, size, sortBy);
    }

    @GetMapping("/{comuneId}")
    public Comune findComuneById(@PathVariable long comuneId){
        return this.comuneService.findById(comuneId);
    }

    @GetMapping("/province")
    public Page<Provincia> findAllProvince(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "30") int size,
                                           @RequestParam(defaultValue = "provincia") String sortBy){
        return this.provinciaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{provinciaId}")
    public Provincia findProvinciaById(@PathVariable long provinciaId){
        return this.provinciaService.findById(provinciaId);
    }
}
