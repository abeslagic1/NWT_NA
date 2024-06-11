package com.etf.controllers;

import com.etf.dtos.Doctor;
import com.etf.dtos.Termin;
import com.etf.feign.TitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/tit/termini")
public class TerminController {

    @Autowired
    private TitClient terminiClient;


    @PostMapping("/")
    ResponseEntity<?> newTermin(@RequestBody Termin newTermin) {

        terminiClient.newTermin(newTermin);
        return ResponseEntity.ok("Termin uspjesno dodan.");
    }

    @GetMapping("/")
    Iterable<Termin> allTermin() {
        return terminiClient.allTermin();
    }



    @GetMapping("/{id}")
    ResponseEntity<?> oneTermin(@PathVariable int id) {
        try{
            return ResponseEntity.ok(terminiClient.oneTermin(id));

        }catch (Exception e){

            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PostMapping("/{id}")
//    Termin replaceTermin(@RequestBody Termin newTermin, @PathVariable int id) {
//        return terminRepository.findById(id).map(termin -> {
//            termin.setKomentar(newTermin.getKomentar());
//            termin.setStatus(newTermin.getStatus());
//            return terminRepository.save(termin);
//        }).orElseGet(() -> {
//            newTermin.setID(id);
//            return terminRepository.save(newTermin);
//        });
//    }

    @DeleteMapping("/{id}")
    void deleteTermin(@PathVariable int id) {
        terminiClient.deleteTermin(id);
    }


    @PutMapping("/updateTermin/{id}")
    ResponseEntity<?> updateTermin(@PathVariable int id, @RequestBody Termin updatedTermin) {
        try{
            terminiClient.updateTermin(id, updatedTermin);
            return ResponseEntity.ok("The termin has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
