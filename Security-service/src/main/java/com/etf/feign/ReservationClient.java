package com.etf.feign;

import com.etf.dtos.*;
import com.etf.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@FeignClient(name="reservation-service", url = "http://localhost:8081")
public interface ReservationClient {

    //ZA REZERVACIJE:

    //POST
    @PostMapping(path = "/Reservation/Rezervacija/")
    ResponseEntity <String> addNewRezervacija(@RequestBody RezervacijaDAO rezervacijaDAO);

    //GET
    @GetMapping(path = "/Reservation/Rezervacija/")
    public @ResponseBody Iterable<Rezervacija> getAllRezervacije();

    //PUT
    @RequestMapping(path = "/Reservation/Rezervacija/pacijentIdById/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateRezervacijaPacijentId(@RequestBody() String pacijentId, @PathVariable(value = "id") Integer id);

    @RequestMapping(path = "/Reservation/Rezervacija/sobaIdById/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateRezervacijaSobaId(@RequestBody String sobaId, @PathVariable("id") Integer id);

    @RequestMapping(path = "/Reservation/Rezervacija/krevetIdById/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateRezervacijaKrevetId(@RequestBody String krevetId, @PathVariable("id") Integer id);

    @RequestMapping(path = "/Reservation/Rezervacija/DatumDolaskaById/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateRezervacijaDatumDolaska(@RequestBody String datumDolaska, @PathVariable("id") Integer id);

    @RequestMapping(path = "/Reservation/Rezervacija/DatumOdlaskaById/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateRezervacijaDatumOdlaska(@RequestBody String datumOdlaska, @PathVariable("id") Integer id);

    //DELETE
    @DeleteMapping(path = "/Reservation/Rezervacija/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteReservation(@PathVariable("id") Integer id);


    // ZA PACIJENTE:

    //POST
    @PostMapping(path = "Reservation/Pacijent/")
    ResponseEntity<String> addNewPacijent(@RequestBody @Valid PacijentDAO pacijentDAO);

    //GET
    @GetMapping(path = "Reservation/Pacijent/")
    public @ResponseBody Iterable<Pacijent> getAllPacijents();

    @GetMapping(path = "Reservation/Pacijent/GetByIme/{ime}")
    public @ResponseBody ResponseEntity<?> getPacijentName(@PathVariable("ime") String ime);

    @GetMapping(path = "Reservation/Pacijent/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getPacijentId(@PathVariable("id") Integer id);


    //PUT
    @RequestMapping(path = "Reservation/Pacijent/Ime/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updatePacijentIme(@RequestBody String ime, @PathVariable("id") Integer id);

    @RequestMapping(path = "Reservation/Pacijent/Prezime/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updatePacijentPrezime(@RequestBody String prezime, @PathVariable("id") Integer id);

    @RequestMapping(path = "Reservation/Pacijent/samUSobi/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updatePacijentSamUSobi(@RequestBody String samUsobi, @PathVariable("id") Integer id);


    //DELETE
    @DeleteMapping(path = "Reservation/Pacijent/byId/{id}")
    public @ResponseBody ResponseEntity<String> deletePacijentById(@PathVariable("id") Integer id);


    //ZA KREVETE:

    //POST
    @PostMapping(path = "Reservation/Krevet/")
    ResponseEntity<String> addNewKrevet(@RequestBody KrevetDAO krevetDAO);


    //GET
    @GetMapping(path = "Reservation/Krevet/")
    public @ResponseBody Iterable<Krevet>getAllKreveti();

    @GetMapping(path = "Reservation/Krevet/GetByNaziv/{nazivKreveta}")
    public @ResponseBody ResponseEntity<?> getKrevetByNaziv(@PathVariable("nazivKreveta") String nazivKreveta);

    @GetMapping(path = "Reservation/Krevet/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getKrevetId(@PathVariable("id") Integer id);


    //PUT
    @RequestMapping(path = "Reservation/Krevet/Naziv/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateKrevetNaziv(@RequestBody String naziv, @PathVariable("id") Integer id);

    @RequestMapping(path = "Reservation/Krevet/SobaID/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateKrevetSobaId(@RequestBody String sobaId, @PathVariable("id") Integer id);

    @RequestMapping(path = "Reservation/Krevet/zauzetost/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateKrevetZauzetost(@RequestBody String zauzetost, @PathVariable("id") Integer id);


    //DELETE
    @DeleteMapping(path = "Reservation/Krevet/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteKrevetById(@PathVariable("id") Integer id);


    //ZA SOBU:


    @PostMapping(path = "Reservation/Soba/")
    ResponseEntity <String> addNewSoba(@RequestBody SobaDAO sobaDAO);

    @GetMapping(path = "Reservation/Soba/")
    public @ResponseBody Iterable<Soba> getAllSobe();

    @GetMapping(path = "Reservation/Soba/GetByNazivSobe/{nazivSobe}")
    public @ResponseBody ResponseEntity<?> getSobaByNazivSobe(@PathVariable("nazivSobe") String nazivSobe);

    @GetMapping(path = "Reservation/Soba/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getSobaById(@PathVariable("id") Integer id);

    @RequestMapping(path = "Reservation/Soba/Naziv/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateSobaNaziv(@RequestBody String naziv, @PathVariable("id") Integer id) ;


    @RequestMapping(path = "Reservation/Soba/zauzetost/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateSobaZauzetost(@RequestBody String zauzetost, @PathVariable("id") Integer id);


    @RequestMapping(path = "Reservation/Soba/privateShared/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> updateSobaPrivateShared(@RequestBody String privateShared, @PathVariable("id") Integer id);

    @DeleteMapping(path = "Reservation/Soba/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSobaById(@PathVariable("id") Integer id);


    //ZA KOMUNIKACIJU REGISTRATION / TIT

    @GetMapping(path = "/Reservation/Rezervacija/komunikacijaSaTit")
    public @ResponseBody RegTitClass komunikacijaSaTit();

}