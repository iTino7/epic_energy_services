package epic_energy_services.bw2.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewFatturaDTO(
        @NotNull(message = "La data è obbligatoria.")
        @Past
        LocalDate date,
        @NotNull(message = "L'importo è obbligatorio.")
        double importo,
        @NotNull(message = "Il numero di fattura è obbligatorio.")
//        @Max(value = 5,message = "Il numero fattura deve essere composto da 4 cifre.")
//        @Min(value = 4,message = "Il numero fattura deve essere composto da 4 cifre.")
        int numero,
        @NotBlank(message = "Lo stato della fattura è obbligatorio.")
        String statoFattura,
        @NotNull(message = "Il numero cliente è obbligatorio.")
        long clienteId
) {
}
