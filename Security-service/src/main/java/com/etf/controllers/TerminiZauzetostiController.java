package com.etf.controllers;

import com.etf.dtos.TerminZauzetosti;
import com.etf.feign.TitClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/tit/terminiZauzetosti")
public class TerminiZauzetostiController {

    @Autowired
    private TitClient TZClient;


    @PostMapping("/")
    ResponseEntity<?> terminZauzetosti(@Valid @RequestBody TerminZauzetosti newTerminZauzetosti) {
        TZClient.terminZauzetosti(newTerminZauzetosti);
        return ResponseEntity.ok("TerminZauzetosti uspjesno dodan.");
    }

    @GetMapping("/")
    Iterable<TerminZauzetosti> allTZ() {
        return TZClient.allTZ();
    }


    @GetMapping("/{tzID}")
    ResponseEntity<?> oneTZ(@PathVariable int tzID) {
        try{
            return ResponseEntity.ok(TZClient.oneTZ(tzID));

        }catch (Exception e){

            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{tzID}")
    ResponseEntity<?> replaceTerminZauzetosti(@RequestBody TerminZauzetosti newTerminZauzetosti, @PathVariable int tzID) {
        try{
            TZClient.replaceTerminZauzetosti(newTerminZauzetosti, tzID);
            return ResponseEntity.ok("The termin has been successfully updated");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tzID}")
    void deleteTerminZauzetosti(@PathVariable int tzID) {
        TZClient.deleteTerminZauzetosti(tzID);
    }

}
