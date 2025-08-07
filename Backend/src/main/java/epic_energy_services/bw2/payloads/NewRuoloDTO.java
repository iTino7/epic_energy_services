package epic_energy_services.bw2.payloads;

import jakarta.validation.constraints.NotBlank;

public record NewRuoloDTO(
        @NotBlank(message = "Il nome del ruolo è obbligatorio")
        String nomeRuolo
) {}