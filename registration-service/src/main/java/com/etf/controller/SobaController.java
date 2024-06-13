package com.etf.controller;

import com.etf.dao.SobaDAO;
import com.etf.exceptions.NotFoundException;
import com.etf.model.Krevet;
import com.etf.model.Soba;
import com.etf.repository.SobaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Soba")
public class SobaController {

    @Autowired
    // This means to get the bean called userRepository
    //Which is auto-generated by Spring, we will use it to handle the data
    private SobaRepository sobaRepository;

    @PostMapping(path = "/")
    ResponseEntity <String> addNewSoba(@RequestBody @Valid SobaDAO sobaDAO, HttpServletRequest request) throws Exception{


        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            Soba s = new Soba();

            s.setNazivSobe(sobaDAO.getNazivSobe());
            s.setZauzetost(sobaDAO.getZauzetost());
            s.setPrivateShared(sobaDAO.getPrivateShared());

            sobaRepository.save(s);
            return ResponseEntity.ok("Room has been added.");
        }
        else{
            throw new Exception("Operation not allowed");
        }


    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Soba> getAllSobe(HttpServletRequest request) throws Exception{
        // This returns a JSON or XML with the users
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return sobaRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping(path = "/GetByNazivSobe/{nazivSobe}")
    public @ResponseBody ResponseEntity<?> getSobaByNazivSobe(@PathVariable("nazivSobe") String nazivSobe, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Soba> soba = sobaRepository.findByNazivSobe(nazivSobe);

                if(soba.isEmpty()) throw new NotFoundException("Soba sa tim nazivom nije pronadjena.");

                return ResponseEntity.ok(soba);

            }catch (NotFoundException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @GetMapping(path = "/GetById/{id}")
    public @ResponseBody ResponseEntity<?> getSobaById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Soba> soba = sobaRepository.findById(id);

                if(soba.isEmpty()) throw new NotFoundException("Soba sa tim id-em nije pronadjena.");

                return ResponseEntity.ok(soba);
            }catch (NotFoundException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping(path = "/Naziv/{id}")
    public @ResponseBody ResponseEntity<String> updateSobaNaziv(@RequestBody String naziv, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception {

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try {
                Optional<Soba> soba = sobaRepository.findById(id);

                if (soba.isEmpty()) throw new NotFoundException("Soba sa tim id-em nije pronadjen.");
                if (naziv == null || naziv.isEmpty() || naziv.isBlank())
                    throw new NotFoundException("Naziv ne smije biti prazan string.");

                Soba soba1 = soba.get();

                soba1.setNazivSobe(naziv);

                sobaRepository.save(soba1);

                return ResponseEntity.ok("The room has been successfully updated");
            } catch (NotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }


    @PutMapping(path = "/zauzetost/{id}")
    public @ResponseBody ResponseEntity<String> updateSobaZauzetost(@RequestBody String zauzetost, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Soba> soba = sobaRepository.findById(id);

                if(soba.isEmpty()) throw new NotFoundException("Soba sa tim id-em nije pronadjen.");
                if(zauzetost == null || zauzetost.isEmpty() || zauzetost.isBlank()) throw new NotFoundException("Polje zauzetost ne smije biti prazan string.");

                Soba soba1 = soba.get();

                if(zauzetost.equals("true") || zauzetost.equals("false")){
                    Boolean zauzet = Boolean.valueOf(zauzetost);

                    soba1.setZauzetost(zauzet);
                    sobaRepository.save(soba1);

                    return ResponseEntity.ok("The room has been successfully updated");
                }
                return new ResponseEntity<>("Nije ispravna vrijednost boolean-a.", HttpStatus.BAD_REQUEST);
            }
            catch (NotFoundException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }


    @PutMapping(path = "/privateShared/{id}")
    public @ResponseBody ResponseEntity<String> updateSobaPrivateShared(@RequestBody String privateShared, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Soba> soba = sobaRepository.findById(id);

                if(soba.isEmpty()) throw new NotFoundException("Soba sa tim id-em nije pronadjen.");
                if(privateShared == null || privateShared.isEmpty() || privateShared.isBlank()) throw new NotFoundException("Private/Shared ne smije biti prazan string.");

                Soba soba1 = soba.get();

                if(privateShared.equals("S") || privateShared.equals("P")){
                    soba1.setPrivateShared(privateShared);

                    sobaRepository.save(soba1);

                    return ResponseEntity.ok("The room has been successfully updated");
                }

                return new ResponseEntity<>("Nije unesena ispravna vrijednost za polje Private/Shared. Unesite P ili S.", HttpStatus.BAD_REQUEST);
            }
            catch (NotFoundException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping(path = "/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSobaById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Soba> soba = sobaRepository.findById(id);

                if(soba.isEmpty()) throw new NotFoundException("Soba sa tim id-em nije pronadjen.");

                sobaRepository.deleteById(id);

                return ResponseEntity.ok("The room has been successfully deleted");
            }
            catch (NotFoundException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
