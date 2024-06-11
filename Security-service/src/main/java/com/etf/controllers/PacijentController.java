package com.etf.controllers;


import com.etf.dtos.Pacijent;
import com.etf.dtos.PacijentDAO;
//import com.etf.feign.PacijentClient;
import com.etf.feign.ReservationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Pacijent")
public class PacijentController {

    @Autowired
    private ReservationClient pacijentClient;

    //POST
    @PostMapping(path = "/")
    ResponseEntity<String> addNewPacijent(@RequestBody PacijentDAO pacijentDAO) {

        pacijentClient.addNewPacijent(pacijentDAO);

        return ResponseEntity.ok("The patient has been successfully added.");
    }

    //GET
    @GetMapping(path = "/")
    public @ResponseBody Iterable<Pacijent> getAllPacijents(){
        //This returns a JSON or XML with the pacijents
        return pacijentClient.getAllPacijents();
    }

    @GetMapping(path = "/GetByIme/{ime}")
    public @ResponseBody ResponseEntity<?> getPacijentName(@PathVariable("ime") String ime){

        try{
            ResponseEntity<?> pacijent = pacijentClient.getPacijentName(ime);

            return ResponseEntity.ok(pacijent.getBody());
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getPacijentId(@PathVariable("id") Integer id){

        try{
            ResponseEntity<?> pacijent = pacijentClient.getPacijentId(id);

            return ResponseEntity.ok(pacijent);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //PUT
    @PutMapping(path = "/Ime/{id}")
    public @ResponseBody ResponseEntity<String> updatePacijentIme(@RequestBody String ime, @PathVariable("id") Integer id){

        try{
            pacijentClient.updatePacijentIme(ime, id);

            return ResponseEntity.ok("The patient has been successfully updated");
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/Prezime/{id}")
    public @ResponseBody ResponseEntity<String> updatePacijentPrezime(@RequestBody String prezime, @PathVariable("id") Integer id){

        try{
            pacijentClient.updatePacijentPrezime(prezime, id);

            return ResponseEntity.ok("The patient has been successfully updated");
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/samUSobi/{id}")
    public @ResponseBody ResponseEntity<String> updatePacijentSamUSobi(@RequestBody String samUsobi, @PathVariable("id") Integer id){

        try{
            pacijentClient.updatePacijentSamUSobi(samUsobi, id);

            return ResponseEntity.ok("The patient has been successfully updated");
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE
    @DeleteMapping(path = "/byId/{id}")
    public @ResponseBody ResponseEntity<String> deletePacijentById(@PathVariable("id") Integer id){

        try{
            pacijentClient.deletePacijentById(id);

            return ResponseEntity.ok("The patient has been successfully deleted");
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
