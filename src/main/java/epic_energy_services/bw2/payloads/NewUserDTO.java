package epic_energy_services.bw2.payloads;

import epic_energy_services.bw2.entities.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record NewUserDTO(
        @NotBlank
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String password,

        String firstName,
        String lastName,
        String avatar) {}