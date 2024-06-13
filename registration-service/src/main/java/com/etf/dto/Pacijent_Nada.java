package com.etf.dto;

//import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Pacijent_Nada")
public class Pacijent_Nada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pacID;

    @Column(name = "ime")
    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @Column(name = "prezime")
    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "termin_pacijent", joinColumns = @JoinColumn(name = "pacijent_id"), inverseJoinColumns = @JoinColumn(name = "termin_id"))
    private List<Termin> terminList;

    public Pacijent_Nada() {
    }

    public Pacijent_Nada(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getID() {
        return pacID;
    }
    public void setID(int pacID) {
        this.pacID = pacID;
    }

    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public List<Termin> getTerminList() {
        return terminList;
    }
    public void setTerminList(List<Termin> terminList) {
        this.terminList = terminList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
          return true;
        if (!(o instanceof Pacijent_Nada))
          return false;
        Pacijent_Nada pacijentNada = (Pacijent_Nada) o;
        return Objects.equals(this.pacID, pacijentNada.pacID) && Objects.equals(this.ime, pacijentNada.ime) && Objects.equals(this.prezime, pacijentNada.prezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pacID, this.ime, this.prezime);
    }

    @Override
    public String toString() {
        return "Pacijent{id=" + pacID + ", ime = " + ime + ", prezime = "+ prezime + "}";
    }


}
