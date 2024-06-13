package com.etf.feign;

import com.etf.dtos.*;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@FeignClient(name="tit-service", url = "http://localhost:8082")
public interface TitClient {


    @PostMapping(path = "/tit/doktori/")
    ResponseEntity<?> newDoctor(@RequestBody Doctor newDoctor);

    @GetMapping(path = "/tit/doktori/")
    Iterable<Doctor> all();

    @GetMapping("/tit/doktori/{docID}")
    ResponseEntity<?> one(@PathVariable int docID);

    @PutMapping("/tit/doktori/replaceDoctor/{docID}")
    Doctor replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable int docID);

    @PutMapping("/tit/doktori/updateDoctor/{docID}")
    Doctor updateDoctor(@PathVariable int docID, @RequestBody Doctor updatedDoctor);

    @DeleteMapping("/tit/doktori/{docID}")
    void deleteDoctor(@PathVariable int docID);


    //ZA TERMINE:

    @GetMapping("/tit/termini/")
    Iterable<Termin> allTermin();

    @PostMapping("/tit/termini/")
    Termin newTermin(@RequestBody Termin newTermin);

    @GetMapping("/tit/termini/{id}")
    Termin oneTermin(@PathVariable int id);

//    @PostMapping("/tit/termini/{id}")
//    Termin replaceTermin(@RequestBody Termin newTermin, @PathVariable int id) ;

    @DeleteMapping("/tit/termini/{id}")
    void deleteTermin(@PathVariable int id);


    @PutMapping("/tit/termini/updateTermin/{id}")
    Termin updateTermin(@PathVariable int id, @RequestBody Termin updatedTermin);


    //ZA TerminZauzetosti


    @GetMapping("/tit/terminiZauzetosti/")
    Iterable<TerminZauzetosti> allTZ();

    @PostMapping("/tit/terminiZauzetosti/")
    TerminZauzetosti terminZauzetosti(@Valid @RequestBody TerminZauzetosti newTerminZauzetosti);

    @GetMapping("/tit/terminiZauzetosti/{tzID}")
    TerminZauzetosti oneTZ(@PathVariable int tzID);

    @PutMapping("/tit/terminiZauzetosti/{tzID}")
    TerminZauzetosti replaceTerminZauzetosti(@RequestBody TerminZauzetosti newTerminZauzetosti, @PathVariable int tzID);

    @DeleteMapping("/tit/terminiZauzetosti/{tzID}")
    void deleteTerminZauzetosti(@PathVariable int tzID);


    //ZA TRETMANE

    @GetMapping("/tit/tretmani/")
    Iterable<Tretman> allTretmani();

    @PostMapping("/tit/tretmani/")
    Tretman newTretman(@Valid @RequestBody Tretman newTretman);

    @GetMapping("/tit/tretmani/{tID}")
    Tretman oneTretman(@PathVariable int tID);

    @PutMapping("/tit/tretmani/{tID}")
    Tretman replaceTretman(@RequestBody Tretman newTretman, @PathVariable int tID);

    @DeleteMapping("/tit/tretmani/{tID}")
    void deleteTretman(@PathVariable int tID);


    //ZA PACIJENTE NADA


    @GetMapping("/tit/pacijenti/")
    Iterable<Pacijent_Nada> allPacijenti();

    @PostMapping("/tit/pacijenti/")
    Pacijent_Nada newPacijent(@Valid @RequestBody Pacijent_Nada newPacijent);

    @GetMapping("/tit/pacijenti/{pacID}")
    Pacijent_Nada onePacijent(@PathVariable int pacID);

    @PutMapping("/tit/pacijenti/replacePacijent/{pacID}")
    Pacijent_Nada replacePacijent(@RequestBody Pacijent_Nada newPacijent, @PathVariable int pacID);

    @DeleteMapping("/tit/pacijenti/{pacID}")
    void deletePacijent(@PathVariable int pacID);

    @PutMapping("/tit/pacijenti/updatePacijent/{pacID}")
    Pacijent_Nada updatePacijent(@PathVariable int pacID, @RequestBody Pacijent_Nada updatedPacijent);

    // komunikacija sa  tit sa Reservation

    @GetMapping(path = "/tit/tretmani/komunikacijaSaReservation")
    public @ResponseBody TitRegClass komunikacijaSaReservation();
}
