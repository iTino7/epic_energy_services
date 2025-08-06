package epic_energy_services.bw2.payloads;

import epic_energy_services.bw2.entities.IndirizzoSedeLegale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewIndirizzoDTO(
        @NotBlank(message = "La via non può essere vuota")
        String via,
        @NotBlank(message = "Il civico non può essere vuoto")
        String civico,
        @NotBlank(message = "La località non puo essere vuota")
        String località,
        @NotBlank(message = "Il cap non puo essere vuoto")
        String cap,
        @NotNull(message = "L'id del comune è obbligatorio")
        Long comuneId
) {
}
