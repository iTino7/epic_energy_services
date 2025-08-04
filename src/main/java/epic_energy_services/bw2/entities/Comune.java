package epic_energy_services.bw2.entities;

import jakarta.persistence.*;

@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codice;
    private String nome;
    @ManyToOne
    private Provincia provincia;

    public Comune() {
    }

    public Comune(String codice, String nome, Provincia provincia) {
        this.codice = codice;
        this.nome = nome;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
