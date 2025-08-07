package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codiceProvincia;
    private String codiceComune;
    private String denominazione;
    @ManyToOne
    private Provincia provincia;

    public Comune() {
    }

    public Comune(String codiceProvincia, String codiceComune, String denominazione, Provincia provincia) {
        this.codiceProvincia = codiceProvincia;
        this.codiceComune = codiceComune;
        this.denominazione = denominazione;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public String getCodiceProvincia() {
        return codiceProvincia;
    }

    public void setCodiceProvincia(String codiceProvincia) {
        this.codiceProvincia = codiceProvincia;
    }

    public String getCodiceComune() {
        return codiceComune;
    }

    public void setCodiceComune(String codiceComune) {
        this.codiceComune = codiceComune;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
