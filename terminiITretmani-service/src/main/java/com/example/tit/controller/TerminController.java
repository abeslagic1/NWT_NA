package com.example.tit.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    Iterable<Termin> allTermin(HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }
    
    @PostMapping("/")
    Termin newTermin(@RequestBody Termin newTermin, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminRepository.save(newTermin);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping("/{id}")
    Termin oneTermin(@PathVariable int id, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PostMapping("/{id}")
    Termin replaceTermin(@RequestBody Termin newTermin, @PathVariable int id, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminRepository.findById(id).map(termin -> {
                termin.setKomentar(newTermin.getKomentar());
                termin.setStatus(newTermin.getStatus());
                return terminRepository.save(termin);
            }).orElseGet(() -> {
                newTermin.setID(id);
                return terminRepository.save(newTermin);
            });
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping("/{id}")
    void deleteTermin(@PathVariable int id, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            terminRepository.deleteById(id);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }


    @PutMapping("/updateTermin/{id}")
    Termin updateTermin(@PathVariable int id, @RequestBody Termin updatedTermin, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

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
        else{
            throw new Exception("Operation not allowed");
        }

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
