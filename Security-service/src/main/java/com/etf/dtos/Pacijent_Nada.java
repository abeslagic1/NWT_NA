package com.etf.dtos;

//import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "Pacijent")
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


//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//          return true;
//        if (!(o instanceof Pacijent))
//          return false;
//        Pacijent pacijent = (Pacijent) o;
//        return Objects.equals(this.pacID, pacijent.pacID) && Objects.equals(this.ime, pacijent.ime) && Objects.equals(this.prezime, pacijent.prezime);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pacID, this.ime, this.prezime);
    }

    @Override
    public String toString() {
        return "Pacijent{id=" + pacID + ", ime = " + ime + ", prezime = "+ prezime + "}";
    }


}
