package epic_energy_services.bw2.CSVReader;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.Provincia;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.repositories.ProvinciaRepository;
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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void readCSVAndSaveComuniInDB(String path) {
        String filePath = new File(path).getAbsolutePath();
        boolean isFirstLine = true;
        int counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                String codiceProvincia = columns[0];

                int codiceComune = 0;
                if (isNumeric(String.valueOf(columns[1]))) {
                    codiceComune = Integer.parseInt(columns[1]);
                } else {
                    codiceComune = counter++;
                }

                String denominazione = columns[2];
                String provincia = columns[3];

                Comune comuneNew = new Comune(codiceProvincia, String.format("%03d", codiceComune) , denominazione, this.provinciaRepository.findByProvincia(provincia));
                this.comuneRepository.save(comuneNew);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
