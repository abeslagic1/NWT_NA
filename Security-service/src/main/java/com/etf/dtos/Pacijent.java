package com.etf.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Pacijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @NotBlank(message = "First name must be entered.")
//    @Size(min = 3, max = 50, message = "Length mus be at least 3 chars long.")
    private String ime;

    //    @NotBlank(message = "Last name must be entered.")
//    @Size(min = 3, max = 50, message = "Length mus be at least 3 chars long.")
    private String prezime;
    private Boolean samUSobi;




    @Override
    public String toString() {
        return "Pacijent{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", samUSobi=" + samUSobi +
                '}';
    }
}