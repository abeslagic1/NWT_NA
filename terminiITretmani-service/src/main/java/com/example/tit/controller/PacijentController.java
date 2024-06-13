package com.example.tit.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.apache.hc.core5.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;

import com.example.tit.dao.PacijentRepository;
import com.example.tit.model.Pacijent_Nada;

import jakarta.validation.Valid;

import com.example.tit.exception.DoctorNotFoundException;
//import com.example.tit.exception.NotFoundException;
import com.example.tit.exception.PacijentNotFoundException;

@Validated
@RestController
@RequestMapping(path = "/tit/pacijenti")
public class PacijentController {
    
    @Autowired
    private final PacijentRepository pacijentRepository;

    PacijentController(PacijentRepository pacijentRepository) {
        this.pacijentRepository = pacijentRepository;
    }

    @GetMapping("/")
    Iterable<Pacijent_Nada> allPacijenti(HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return pacijentRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    // @GetMapping("/{ime}")
    // public @ResponseBody ResponseEntity<?> getPacijenetName(@PathVariable("ime") String ime) {

    //     try{
    //         Pacijent pacijent = pacijentRepository.findByIme(ime);

    //         if(pacijent == null) throw new NotFoundException("Pacijent nije pronađen.");

    //         return ResponseEntity.ok(pacijent);
    //     }catch (NotFoundException e) {
    //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }  
    // }

    // @GetMapping("/{prezime}")
    // public @ResponseBody ResponseEntity<?> getPacijenetSurname(@PathVariable("prezime") String prezime) {

    //     try{
    //         Pacijent pacijent = pacijentRepository.findByPrezime(prezime);

    //         if(pacijent == null) throw new NotFoundException("Pacijent nije pronađen.");

    //         return ResponseEntity.ok(pacijent);
    //     }catch (NotFoundException e) {
    //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }  
    // }


    @PostMapping("/")
    Pacijent_Nada newPacijent(@Valid @RequestBody Pacijent_Nada newPacijentNada, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return pacijentRepository.save(newPacijentNada);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping("/{pacID}")
    Pacijent_Nada onePacijent(@PathVariable int pacID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return pacijentRepository.findById(pacID).orElseThrow(() -> new PacijentNotFoundException(pacID));
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping("/replacePacijent/{pacID}")
    Pacijent_Nada replacePacijent(@RequestBody Pacijent_Nada newPacijentNada, @PathVariable int pacID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return pacijentRepository.findById(pacID).map(pacijent -> {
                        pacijent.setIme(newPacijentNada.getIme());
                        pacijent.setPrezime((newPacijentNada.getPrezime()));
                        return pacijentRepository.save(pacijent);
                    })
                    .orElseGet(() -> {
                        newPacijentNada.setID(pacID);
                        return pacijentRepository.save(newPacijentNada);
                    });
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping("/{pacID}")
    void deletePacijent(@PathVariable int pacID, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            pacijentRepository.deleteById(pacID);
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping("/updatePacijent/{pacID}")
    Pacijent_Nada updatePacijent(@PathVariable int pacID, @RequestBody Pacijent_Nada updatedPacijentNada, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return pacijentRepository.findById(pacID).map(pacijent -> {
                if (updatedPacijentNada.getIme() != null) {
                    pacijent.setIme(updatedPacijentNada.getIme());
                }
                if (updatedPacijentNada.getPrezime() != null) {
                    pacijent.setPrezime(updatedPacijentNada.getPrezime());
                }
                return pacijentRepository.save(pacijent);
            }).orElseThrow(() -> new DoctorNotFoundException(pacID));
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    //@PostMapping(path = "/addPacijent")
    //public @ResponseBody String addNewPacijent(@RequestBody String ime, @RequestBody String prezime) {
      //  Pacijent pacijent = new Pacijent();
       // pacijent.setIme(ime);
        //pacijent.setPrezime(prezime);
        //pacijentRepository.save(pacijent);
        //return "Saved";
    //}
     
    //@GetMapping(path = "/allPacijent")
    //public @ResponseBody Iterable<Pacijent> getAllPacijenti() {
      //  return pacijentRepository.findAll();
    //}
    
}
