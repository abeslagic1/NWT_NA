package com.etf.controllers;

import com.etf.dtos.Pacijent_Nada;
import com.etf.feign.TitClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/tit/pacijenti")
public class PacijentNadaController {

    @Autowired
    private TitClient pacijentiNadaClient;


    @GetMapping("/")
    Iterable<Pacijent_Nada> allPacijenti() {
        return pacijentiNadaClient.allPacijenti();
    }


    @PostMapping("/")
    ResponseEntity<?> newPacijent(@Valid @RequestBody Pacijent_Nada newPacijentNada) {
        pacijentiNadaClient.newPacijent(newPacijentNada);

        return ResponseEntity.ok("Uspjesno dodan pacijent");
    }

    @GetMapping("/{pacID}")
    ResponseEntity<?> onePacijent(@PathVariable int pacID) {
        try{
            return ResponseEntity.ok(pacijentiNadaClient.onePacijent(pacID));
        }catch (Exception e){
            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/replacePacijent/{pacID}")
     ResponseEntity<?> replacePacijent(@RequestBody Pacijent_Nada newPacijentNada, @PathVariable int pacID) {
        try{
            pacijentiNadaClient.replacePacijent(newPacijentNada, pacID);
            return ResponseEntity.ok("The pacijent has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{pacID}")
    void deletePacijent(@PathVariable int pacID) {
        pacijentiNadaClient.deletePacijent(pacID);
    }

    @PutMapping("/updatePacijent/{pacID}")
    ResponseEntity<?> updatePacijent(@PathVariable int pacID, @RequestBody Pacijent_Nada updatedPacijentNada) {
        try{
            pacijentiNadaClient.updatePacijent(pacID, updatedPacijentNada);
            return ResponseEntity.ok("The pacijent has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
