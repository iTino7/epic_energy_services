package epic_energy_services.bw2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewClienteDTO {
    @NotBlank(message = "Ragione sociale è obbligatoria!")
    @Size(min = 2, message = "Ragione sociale deve avere almeno 3 caratteri!")
    String ragioneSociale;
    @NotNull(message = "La partita iva è obbligatoria")
    int partitaIva;
    @NotBlank(message = "L'email è obbligatoria!")
    @Email
    String email;
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
    //@NotBlank(message = "Il logo aziendale è obbligatorio")
    //String logoAziendale


    public NewClienteDTO(String ragioneSociale, int partitaIva, String email, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String telefonoContatto, String cognomeContatto) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.telefonoContatto = telefonoContatto;
        this.cognomeContatto = cognomeContatto;
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
