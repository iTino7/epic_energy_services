package epic_energy_services.bw2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record NewClienteDTO(
        @NotBlank(message = "La ragione sociale è obbligatoria.")
        String ragioneSociale,
        @NotNull(message = "La partita IVA è obbligatoria.")
        Long partitaIva,
        @NotBlank(message = "L'email è obbligatoria.")
        @Email(message = "Formato email errato.")
        String email,
        @NotNull(message = "La data di ultimo contatto è obbligatoria.")
        @Past(message = "La data di ultimo contatto deve essere passata.")
        LocalDate dataUltimoContatto,
        @NotNull(message = "Il fatturato annuale è obbligatorio.")
        double fatturatoAnnuale,
        @NotBlank(message = "L'pec è obbligatoria.")
        @Email(message = "Formato pec errato.")
        String pec,
        @NotBlank(message = "Il numero di telefono è obbligatorio.")
        String telefono,
        @NotBlank(message = "L'email di contatto è obbligatoria.")
        @Email(message = "Formato email di contatto errato.")
        String emailContatto,
        @NotBlank(message = "Il nome contatto è obbligatorio.")
        String nomeContatto,
        @NotBlank(message = "Il cognome contatto è obbligatorio.")
        String cognomeContatto,
        @NotBlank(message = "Il numero di telefono contatto è obbligatorio.")
        String telefonoContatto,

        // ********* SEDE LEGALE ********
        @NotBlank(message = "La via non può essere vuota")
        String viaSL,
        @NotBlank(message = "Il civico non può essere vuoto")
        String civicoSL,
        @NotBlank(message = "La località non puo essere vuota")
        String localitaSL,
        @NotBlank(message = "Il cap non puo essere vuoto")
        String capSL,
        @NotNull(message = "L'id del comune è obbligatorio")
        Long comuneIdSL,

        // ********* SEDE OPERATIVA ********
        @NotBlank(message = "La via non può essere vuota")
        String viaSO,
        @NotBlank(message = "Il civico non può essere vuoto")
        String civicoSO,
        @NotBlank(message = "La località non puo essere vuota")
        String localitaSO,
        @NotBlank(message = "Il cap non puo essere vuoto")
        String capSO,
        @NotNull(message = "L'id del comune è obbligatorio")
        Long comuneIdSO
) {
}
