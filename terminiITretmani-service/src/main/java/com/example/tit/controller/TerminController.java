package com.example.tit.controller;

import org.springframework.validation.annotation.Validated;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tit.dao.TerminRepository;
import com.example.tit.model.Termin;
//import com.fasterxml.jackson.databind.JsonNode;
import com.example.tit.exception.TerminNotFoundException;

@Validated
@RestController
@RequestMapping(path = "/tit/termini")
public class TerminController {

    private final TerminRepository terminRepository;

    TerminController(TerminRepository terminRepository) {
        this.terminRepository = terminRepository;
    }

    @GetMapping("/")
    Iterable<Termin> allTermin() {
        return terminRepository.findAll();
    }
    
    @PostMapping("/")
    Termin newTermin(@RequestBody Termin newTermin) {
        return terminRepository.save(newTermin);
    }

    @GetMapping("/{id}")
    Termin oneTermin(@PathVariable int id) {
        return terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
    }

    @PostMapping("/{id}")
    Termin replaceTermin(@RequestBody Termin newTermin, @PathVariable int id) {
        return terminRepository.findById(id).map(termin -> {
            termin.setKomentar(newTermin.getKomentar());
            termin.setStatus(newTermin.getStatus());
            return terminRepository.save(termin);
        }).orElseGet(() -> {
            newTermin.setID(id);
            return terminRepository.save(newTermin);
        });
    }

    @DeleteMapping("/{id}")
    void deleteTermin(@PathVariable int id) {
        terminRepository.deleteById(id);
    }


    @PutMapping("/updateTermin/{id}")
    Termin updateTermin(@PathVariable int id, @RequestBody Termin updatedTermin) {
        return terminRepository.findById(id).map(termin -> {
            if (updatedTermin.getKomentar() != null) {
                termin.setKomentar(updatedTermin.getKomentar());
            }
            if (updatedTermin.getStatus() != null) {
                termin.setStatus(updatedTermin.getStatus());
            }
            return terminRepository.save(termin);
        }).orElseThrow(() -> new TerminNotFoundException(id));
    }


    //@PostMapping(path = "/addTermin")
    //public @ResponseBody String addNewTermin(@RequestBody String status, @RequestBody String komentar ) {
      //  Termin termin = new Termin();
       // termin.setStatus(status);
        //termin.setKomentar(komentar);
        //terminRepository.save(termin);
        //return "Saved";
    //}    

    //@GetMapping(path = "/allTermin")
    //public @ResponseBody Iterable<Termin> getAllTermin() {
      //  return terminRepository.findAll();
    //}
}
