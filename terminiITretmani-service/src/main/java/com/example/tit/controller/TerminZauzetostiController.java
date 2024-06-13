package com.example.tit.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

//import java.sql.Date;

//import org.hibernate.mapping.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tit.dao.TerminZauzetostiRepository;
import com.example.tit.model.TerminZauzetosti;

import jakarta.validation.Valid;

import com.example.tit.exception.TerminZauzetostiNotFoundException;

@Validated
@RestController
@RequestMapping(path = "/tit/terminiZauzetosti")
public class TerminZauzetostiController {

    private final TerminZauzetostiRepository terminZauzetostiRepository;

    TerminZauzetostiController(TerminZauzetostiRepository terminZauzetostiRepository) {
        this.terminZauzetostiRepository = terminZauzetostiRepository;
    }

    @GetMapping("/")
    Iterable<TerminZauzetosti> allTZ(HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminZauzetostiRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PostMapping("/")
    TerminZauzetosti terminZauzetosti(@Valid @RequestBody TerminZauzetosti newTerminZauzetosti, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminZauzetostiRepository.save(newTerminZauzetosti);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping("/{tzID}")
    TerminZauzetosti oneTZ(@PathVariable int tzID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminZauzetostiRepository.findById(tzID).orElseThrow(() -> new TerminZauzetostiNotFoundException(tzID));
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping("/{tzID}")
    TerminZauzetosti replaceTerminZauzetosti(@RequestBody TerminZauzetosti newTerminZauzetosti, @PathVariable int tzID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return terminZauzetostiRepository.findById(tzID).map(terminZauzetosti -> {
                        terminZauzetosti.setDatumTretmana(newTerminZauzetosti.getDatumTretmana());
                        terminZauzetosti.setVrijemePocetka(newTerminZauzetosti.getVrijemePocetka());
                        terminZauzetosti.setVrijemeKraja(newTerminZauzetosti.getVrijemeKraja());
                        return terminZauzetostiRepository.save(terminZauzetosti);
                    })
                    .orElseGet(() -> {
                        newTerminZauzetosti.setID(tzID);
                        return terminZauzetostiRepository.save(newTerminZauzetosti);
                    });
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping("/{tzID}")
    void deleteTerminZauzetosti(@PathVariable int tzID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            terminZauzetostiRepository.deleteById(tzID);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }



    //@PostMapping(path = "/addTerminZauzetosti")
    //public @ResponseBody String addNewTerminZauzetosti(@RequestParam Date datumTretmana, @RequestParam Date vrijemePocetka, @RequestParam Date vrijemeKraja) {
      //  TerminZauzetosti terminZauzetosti = new TerminZauzetosti();
        //terminZauzetosti.setDatumTretmana(datumTretmana);
        //terminZauzetosti.setVrijemePocetka(vrijemePocetka);
        //terminZauzetosti.setVrijemeKraja(vrijemeKraja);
        //terminZauzetostiRepository.save(terminZauzetosti);
        //return "Saved";
    //}

    //@GetMapping(path = "/allTerminZauzetosti")
    //public @ResponseBody Iterable<TerminZauzetosti> getAllTerminZauzetosti() {
      //  return terminZauzetostiRepository.findAll();
    //}    
}
