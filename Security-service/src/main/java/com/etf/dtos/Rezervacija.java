package com.etf.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pacijentId;

    private Integer sobaId;

    private Integer krevetId;

    @JsonFormat(pattern="dd.MM.yyyy HH:mm", timezone="Europe/Zagreb")
    private Date datumDolaska;

    @JsonFormat(pattern="dd.MM.yyyy HH:mm", timezone="Europe/Zagreb")
    private Date datumOdlaska;


}
