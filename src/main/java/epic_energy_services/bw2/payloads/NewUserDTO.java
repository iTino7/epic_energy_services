package epic_energy_services.bw2.payloads;

import epic_energy_services.bw2.entities.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record NewUserDTO(
        @NotBlank(message = "Username è obbligatorio.")
        String username,

        @NotBlank(message = "L'email è obbligatoria.")
        @Email
        String email,

        @NotBlank(message = "La password è obbligatoria.")
        @Size(min = 6)
        String password,

        @NotBlank(message = "Il nome è obbligatorio.")
        String firstName,
        @NotBlank(message = "Il cognome è obbligatorio.")
        String lastName,
        String avatar) {}