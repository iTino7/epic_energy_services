package epic_energy_services.bw2.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class NewClienteDTO {
    @NotBlank(message = "Ragione sociale è obbligatoria!")
    @Size(min = 2, message = "Ragione sociale deve avere almeno 3 caratteri!")
    String ragioneSociale;
    @NotNull(message = "La partita iva è obbligatoria")
    int partitaIva;
    @NotBlank(message = "L'email è obbligatoria!")
    @Email
    String email;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "La data di inserimento è obbligatoria!")
    LocalDate dataInserimento;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "La data dell'ultimo contratto è obbligatoria!")
    LocalDate dataUltimoContratto;
    @NotNull(message = "Il fatturato annuale è obbligatorio!")
    double fatturatoAnnuale;
    @NotBlank(message = "La pec è obbligatoria!")
    @Email
    String pec;
    @NotBlank(message = "Il numero di telefono è obbligatorio!")
    String telefono;
    @NotBlank(message = "L'email di contatto è obbligaotrio!")
    String emailContatto;
    @NotBlank(message = "Il cognome di contatto è obbligatorio!")
    String cognomeContatto;
    @NotBlank(message = "Il telefono di contatto è obbligatorio!")
    String telefonoContatto;
    @NotBlank(message = "Il logo aziendale è obbligatorio")
    String logoAziendale;


    public NewClienteDTO(String ragioneSociale, int partitaIva, String email, LocalDate dataUltimoContratto, LocalDate dataInserimento, double fatturatoAnnuale, String pec, String emailContatto, String telefono, String cognomeContatto, String telefonoContatto, String logoAziendale) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataUltimoContratto = dataUltimoContratto;
        this.dataInserimento = dataInserimento;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.emailContatto = emailContatto;
        this.telefono = telefono;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public int getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(int partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public LocalDate getDataUltimoContratto() {
        return dataUltimoContratto;
    }

    public void setDataUltimoContratto(LocalDate dataUltimoContratto) {
        this.dataUltimoContratto = dataUltimoContratto;
    }

    public double getFatturatoAnnuale() {
        return fatturatoAnnuale;
    }

    public void setFatturatoAnnuale(double fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getEmailContatto() {
        return emailContatto;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public String getTelefonoContatto() {
        return telefonoContatto;
    }

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public String getCognomeContatto() {
        return cognomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public String getLogoAziendale() {
        return logoAziendale;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    @Override
    public String toString() {
        return "NewClienteDTO{" +
                "ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva=" + partitaIva +
                ", email='" + email + '\'' +
                ", pec='" + pec + '\'' +
                ", telefono='" + telefono + '\'' +
                ", emailContatto='" + emailContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto='" + telefonoContatto + '\'' +
                '}';
    }
}
