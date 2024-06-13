package com.example.tit.model;

import com.example.tit.dto.Soba;

public class TitRegClass {


    private Iterable<Tretman> tretman;
    private Iterable<Soba> soba;


    public TitRegClass( Iterable<Tretman> tretman, Iterable<Soba> soba) {
        this.soba = soba;
        this.tretman = tretman;
    }

    public Iterable<Soba> getSoba() {
        return soba;
    }

    public void setSoba(Iterable<Soba> soba) {
        this.soba = soba;
    }

    public Iterable<Tretman> getTretman() {
        return tretman;
    }

    public void setTretman(Iterable<Tretman> tretman) {
        this.tretman = tretman;
    }
}
