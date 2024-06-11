package com.etf.controllers;

import com.etf.dtos.Doctor;
import com.etf.exceptions.DoctorNotFoundException;
import com.etf.feign.TitClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping(path = "/tit/doktori")
public class DoktorController {

    @Autowired
    private TitClient doktorClient;

    //ZA DOKTORA:


    //POST
    @PostMapping(path = "/")
    ResponseEntity<?> newDoctor(@RequestBody Doctor newDoctor) {

        doktorClient.newDoctor(newDoctor);

        return ResponseEntity.ok("Doktor uspjesno dodan");
    }

    //GET
    @GetMapping(path = "/")
    public Iterable<Doctor> all() {
        return doktorClient.all();
    }

    @GetMapping("/{docID}")
    ResponseEntity<?> one(@PathVariable int docID) {

        try{
            return ResponseEntity.ok(doktorClient.one(docID).getBody());
        }catch (Exception e){

            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //PUT

    @PutMapping("/replaceDoctor/{docID}")
    ResponseEntity<?> replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable int docID) {

        try{
            doktorClient.replaceDoctor(newDoctor, docID);
            return ResponseEntity.ok("Uspjesno zamjenjen doktor.");
        }catch (Exception e){
            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDoctor/{docID}")
    ResponseEntity<?> updateDoctor(@PathVariable int docID, @RequestBody Doctor updatedDoctor) {

        try{
            doktorClient.updateDoctor(docID, updatedDoctor);
            return ResponseEntity.ok("The doctor has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE

    @DeleteMapping("/{docID}")
    void deleteDoctor(@PathVariable int docID) {
        doktorClient.deleteDoctor(docID);
    }
}
