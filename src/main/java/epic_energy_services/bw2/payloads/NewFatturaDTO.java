package epic_energy_services.bw2.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewFatturaDTO(
        @NotNull(message = "La data è obbligatoria.")
        @Past
        LocalDate date,
        @NotNull(message = "L'importo è obbligatorio.")
        Double importo,
        @NotNull(message = "Il numero di fattura è obbligatorio.")
        Integer numero,
        @NotBlank(message = "Lo stato della fattura è obbligatorio.")
        String statoFattura,
        @NotNull(message = "Il numero cliente è obbligatorio.")
        Long clienteId
) {
}
