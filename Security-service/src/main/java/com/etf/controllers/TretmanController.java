package com.etf.controllers;

import com.etf.dtos.Tretman;
import com.etf.feign.TitClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/tit/tretmani")
public class TretmanController {

    @Autowired
    private TitClient tretmanClient;


    @GetMapping("/")
    Iterable<Tretman> allTretmani() {
        return tretmanClient.allTretmani();
    }

    @PostMapping("/")
    ResponseEntity<?> newTretman(@Valid @RequestBody Tretman newTretman) {
        tretmanClient.newTretman(newTretman);

        return ResponseEntity.ok("Uspjesno dodan tretman");
    }

    @GetMapping("/{tID}")
    ResponseEntity<?> oneTretman(@PathVariable int tID) {
        try{
            return ResponseEntity.ok(tretmanClient.oneTretman(tID));
        }catch (Exception e){
            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{tID}")
    ResponseEntity<?> replaceTretman(@RequestBody Tretman newTretman, @PathVariable int tID) {
        try{
            tretmanClient.replaceTretman(newTretman, tID);
            return ResponseEntity.ok("The tretman has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tID}")
    void deleteTretman(@PathVariable int tID) {
        tretmanClient.deleteTretman(tID);
    }

}
