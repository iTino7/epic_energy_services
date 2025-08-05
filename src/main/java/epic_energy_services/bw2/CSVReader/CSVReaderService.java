package epic_energy_services.bw2.CSVReader;

import epic_energy_services.bw2.entities.Comune;
import epic_energy_services.bw2.entities.Provincia;
import epic_energy_services.bw2.repositories.ComuneRepository;
import epic_energy_services.bw2.repositories.ProvinciaRepository;
import epic_energy_services.bw2.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class CSVReaderService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaService provinciaService;

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
                Provincia provincia = this.provinciaRepository.findByProvincia(columns[3]);
                switch (columns[3]) {
                    case "Valle d'Aosta/Vallée d'Aoste":
                        provincia = this.provinciaService.findById(4L);
                        break;
                    case "La Spezia":
                        provincia = this.provinciaService.findById(45L);
                        break;
                    case "Verbano-Cusio-Ossola":
                        provincia = this.provinciaService.findById(105L);
                        break;
                    case "Monza e della Brianza":
                        provincia = this.provinciaService.findById(60L);
                        break;
                    case "Bolzano/Bozen":
                        provincia = this.provinciaService.findById(17L);
                        break;
                    case "Reggio nell'Emilia":
                        provincia = this.provinciaService.findById(83L);
                        break;
                    case "Forlì-Cesena":
                        provincia = this.provinciaService.findById(38L);
                        break;
                    case "Pesaro e Urbino":
                        provincia = this.provinciaService.findById(72L);
                        break;
                    case "Ascoli Piceno":
                        provincia = this.provinciaService.findById(7L);
                        break;
                    case "Reggio Calabria":
                        provincia = this.provinciaService.findById(82L);
                        break;
                    case "Vibo Valentia":
                        provincia = this.provinciaService.findById(108L);
                        break;
                    case "Sud Sardegna":
                        provincia = this.provinciaService.findById(20L);
                        break;
                    default:
                        break;

                }

                Comune comuneNew = new Comune(codiceProvincia, String.format("%03d", codiceComune) , denominazione, provincia);
                this.comuneRepository.save(comuneNew);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
