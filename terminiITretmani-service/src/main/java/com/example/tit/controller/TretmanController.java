package com.example.tit.controller;

import com.example.tit.Feign.ReservationClient;
import com.example.tit.dto.Soba;
import com.example.tit.model.TitRegClass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tit.dao.TretmanRepository;
import com.example.tit.model.Tretman;

import jakarta.validation.Valid;

import com.example.tit.exception.TretmanNotFoundException;

@Validated
@RestController
@RequestMapping(path = "/tit/tretmani")
public class TretmanController {

    private final TretmanRepository tretmanRepository;

    TretmanController(TretmanRepository tretmanRepository) {
        this.tretmanRepository = tretmanRepository;
    }

    @GetMapping("/")
    Iterable<Tretman> allTretmani(HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return tretmanRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PostMapping("/")
    Tretman newTretman(@Valid @RequestBody Tretman newTretman, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return tretmanRepository.save(newTretman);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping("/{tID}")
    Tretman oneTretman(@PathVariable int tID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return tretmanRepository.findById(tID)
                    .orElseThrow(() -> new TretmanNotFoundException(tID));
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping("/{tID}")
    Tretman replaceTretman(@RequestBody Tretman newTretman, @PathVariable int tID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return tretmanRepository.findById(tID)
                    .map(tretman -> {
                        tretman.setNaziv(newTretman.getNaziv());
                        tretman.setOpis(newTretman.getOpis());
                        return tretmanRepository.save(tretman);
                    }).orElseGet(() ->  {
                        newTretman.setID(tID);
                        return tretmanRepository.save(newTretman);
                    });
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping("/{tID}")
    void deleteTretman(@PathVariable int tID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            tretmanRepository.deleteById(tID);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }



    
    //@PostMapping(path = "/addTretman")
    //public @ResponseBody String addNewTretman(@RequestBody String naziv, @RequestBody String opis) {
       // Tretman tretman = new Tretman();
        //tretman.setNaziv(naziv);
        //tretman.setOpis(opis);
        //tretmanRepository.save(tretman);
        //return "Saved";
    //}

    //@GetMapping(path = "/allTretman")
    //public @ResponseBody Iterable<Tretman> getAllTretman() {
      //  return tretmanRepository.findAll();
    //}


    // Poziv koji sadrzi i odgovor iz Reservation servisa


    @Autowired
    private ReservationClient sobaClient;

    @GetMapping(path = "komunikacijaSaReservation")
    public @ResponseBody TitRegClass komunikacijaSaReservation(){

        Iterable<Tretman> tretman = tretmanRepository.findAll();

        Iterable<Soba> soba = sobaClient.getAllSobe();

        TitRegClass titRegClass = new TitRegClass(tretman,  soba);

        return titRegClass;
    }
    
}
