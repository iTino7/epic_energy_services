package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "clienti")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ragioneSociale;
    private int partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContratto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @OneToMany
    private List<Indirizzo> indirizzi;

    public Cliente() {
    }

    public Cliente(String ragioneSociale, int partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContratto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognomeContatto, String telefonoContatto, String logoAziendale) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContratto = dataUltimoContratto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
    }

    public List<Indirizzo> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(List<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public long getId() {
        return id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNomeContatto() {
        return nomeContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public String getCognomeContatto() {
        return cognomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public String getTelefonoContatto() {
        return telefonoContatto;
    }

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public String getLogoAziendale() {
        return logoAziendale;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva=" + partitaIva +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContratto=" + dataUltimoContratto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono='" + telefono + '\'' +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto='" + telefonoContatto + '\'' +
                ", logoAziendale='" + logoAziendale + '\'' +
                '}';
    }
}
