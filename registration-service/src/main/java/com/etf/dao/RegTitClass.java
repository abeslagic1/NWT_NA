package com.etf.dao;

import com.etf.dto.Tretman;
import com.etf.model.Rezervacija;

public class RegTitClass {

    private Iterable<Rezervacija> rezervacija;
    private Iterable<Tretman> tretman;

    public RegTitClass(Iterable<Rezervacija> rezervacija, Iterable<Tretman> tretman) {
        this.rezervacija = rezervacija;
        this.tretman = tretman;
    }

    public Iterable<Rezervacija> getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Iterable<Rezervacija> rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Iterable<Tretman> getTretman() {
        return tretman;
    }

    public void setTretman(Iterable<Tretman> tretman) {
        this.tretman = tretman;
    }
}
