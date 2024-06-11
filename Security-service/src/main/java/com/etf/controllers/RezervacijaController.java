package com.etf.controllers;


import com.etf.dtos.Rezervacija;
import com.etf.dtos.RezervacijaDAO;
import com.etf.exceptions.NotFoundException;
import com.etf.feign.ReservationClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Rezervacija")
public class RezervacijaController {

    @Autowired
    private ReservationClient rezervacijaClient;



    @GetMapping(path = "/")
    public @ResponseBody Iterable<Rezervacija> getAllRezervacije() {

        return rezervacijaClient.getAllRezervacije();
    }

    @PostMapping(path = "/")
    ResponseEntity <String> addNewRezervacija(@RequestBody RezervacijaDAO rezervacijaDAO){

        try {
            rezervacijaClient.addNewRezervacija(rezervacijaDAO);

            return ResponseEntity.ok("Reservation has been added.");

        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping(path = "/pacijentIdById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaPacijentId(@RequestBody String pacijentId, @PathVariable("id") Integer id){

        try{
            rezervacijaClient.updateRezervacijaPacijentId(pacijentId,id);

            return ResponseEntity.ok("Reservation has been successfully updated.");

        }catch (NotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping(path = "/sobaIdById/{id}")
    public @ResponseBody ResponseEntity<?> updateRezervacijaSobaId(@RequestBody String sobaId, @PathVariable("id") Integer id){

        try{
            rezervacijaClient.updateRezervacijaSobaId(sobaId, id);

            return ResponseEntity.ok("Reservation has been successfully updated.");

        }catch (NotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "krevetIdById/{id}")
    public @ResponseBody ResponseEntity<?> updateRezervacijaKrevetId(@RequestBody String krevetId, @PathVariable("id") Integer id){

        try{
            rezervacijaClient.updateRezervacijaKrevetId(krevetId, id);

            return ResponseEntity.ok("Reservation has been successfully updated.");

        }catch (NotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "DatumDolaskaById/{id}")
    public @ResponseBody ResponseEntity<?> updateRezervacijaDatumDolaska(@RequestBody String datumDolaska, @PathVariable("id") Integer id){

        try{
            rezervacijaClient.updateRezervacijaDatumDolaska(datumDolaska, id);

            return ResponseEntity.ok("Reservation has been successfully updated.");

        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "DatumOdlaskaById/{id}")
    public @ResponseBody ResponseEntity<?> updateRezervacijaDatumOdlaska(@RequestBody String datumOdlaska, @PathVariable("id") Integer id){

        try{
            rezervacijaClient.updateRezervacijaDatumOdlaska(datumOdlaska, id);

            return ResponseEntity.ok("Reservation has been successfully updated.");

        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "byId/{id}")
    public @ResponseBody ResponseEntity<?> deleteReservation(@PathVariable("id") Integer id){

        try {
            rezervacijaClient.deleteReservation(id);

            return ResponseEntity.ok("Reservation has been successfully deleted.");

        }catch (NotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}