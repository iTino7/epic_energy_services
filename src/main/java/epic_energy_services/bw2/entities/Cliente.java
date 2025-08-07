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
    private long partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @OneToOne
    private IndirizzoSedeLegale sedeLegale;
    @OneToMany(mappedBy = "cliente")
    private List<IndirizzoSedeOperativa> sediOperative;

    public Cliente() {
    }

    public Cliente(String ragioneSociale, long partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognomeContatto, String telefonoContatto, IndirizzoSedeLegale sedeLegale) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = "https://ui-avatars.com/api/?name=" + nomeContatto + "+" + cognomeContatto;
        this.sedeLegale = sedeLegale;
    }

    public IndirizzoSedeLegale getSedeLegale() {
        return sedeLegale;
    }

    public void setSedeLegale(IndirizzoSedeLegale sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public List<IndirizzoSedeOperativa> getSediOperative() {
        return sediOperative;
    }

    public void setSediOperative(List<IndirizzoSedeOperativa> sediOperative) {
        this.sediOperative = sediOperative;
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

    public long getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(long partitaIva) {
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

    public LocalDate getDataUltimoContatto() {
        return dataUltimoContatto;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
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
                ", dataUltimoContatto=" + dataUltimoContatto +
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
