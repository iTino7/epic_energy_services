package epic_energy_services.bw2.CSVReader;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.Provincia;
import epic_energy_services.bw2.repository.ComuneRepository;
import epic_energy_services.bw2.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CSVReaderService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;



    public void readCSVAndSaveProvinceInDB(String path) {
        String filePath = new File(path).getAbsolutePath();
        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                String sigla = columns[0];
                String provincia = columns[1];
                String regione = columns[2];

                Provincia provinciaNew = new Provincia(sigla, provincia, regione);
                this.provinciaRepository.save(provinciaNew);

                // System.out.println("SIGLA: " + sigla + ", PROVINCIA: " + provincia + ", REGIONE: " + regione);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
