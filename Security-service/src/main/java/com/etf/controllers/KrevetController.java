package com.etf.controllers;

import com.etf.dtos.Krevet;
import com.etf.dtos.KrevetDAO;
import com.etf.exceptions.NotFoundException;
import com.etf.feign.ReservationClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Krevet")
public class KrevetController {


    @Autowired
    private ReservationClient krevetClient;

    @PostMapping(path = "/")
    ResponseEntity<String> addNewKrevet(@RequestBody @Valid KrevetDAO krevetDAO){

        krevetClient.addNewKrevet(krevetDAO);

        return ResponseEntity.ok("Bed has been added.");
    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Krevet>getAllKreveti(){

        return krevetClient.getAllKreveti();
    }

    @GetMapping(path = "/GetByNaziv/{nazivKreveta}")
    public @ResponseBody ResponseEntity<?> getKrevetByNaziv(@PathVariable("nazivKreveta") String nazivKreveta){

        try{
            ResponseEntity<?> krevet = krevetClient.getKrevetByNaziv(nazivKreveta);

            return ResponseEntity.ok(krevet.getBody());
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getKrevetId(@PathVariable("id") Integer id){

        try {
            ResponseEntity<?> krevet = krevetClient.getKrevetId(id);

            return  ResponseEntity.ok(krevet.getBody());
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/Naziv/{id}")
    public @ResponseBody ResponseEntity<String> updateKrevetNaziv(@RequestBody String naziv, @PathVariable("id") Integer id){

        try{
            krevetClient.updateKrevetNaziv(naziv, id);

            return ResponseEntity.ok("The bed has been successfully updated");
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/SobaID/{id}")
    public @ResponseBody ResponseEntity<String> updateKrevetSobaId(@RequestBody String sobaId, @PathVariable("id") Integer id){

        try{
            krevetClient.updateKrevetSobaId(sobaId, id);

            return ResponseEntity.ok("The bed has been successfully updated");
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/zauzetost/{id}")
    public @ResponseBody ResponseEntity<String> updateKrevetZauzetost(@RequestBody String zauzetost, @PathVariable("id") Integer id){

        try{
            krevetClient.updateKrevetZauzetost(zauzetost, id);

            return ResponseEntity.ok("The bed has been successfully updated");
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteKrevetById(@PathVariable("id") Integer id){

        try{
            krevetClient.deleteKrevetById(id);

            return ResponseEntity.ok("The bed has been successfully deleted");
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
