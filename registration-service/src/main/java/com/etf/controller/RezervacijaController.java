package com.etf.controller;

import com.etf.Feign.TitClient;
import com.etf.dao.RegTitClass;
import com.etf.dao.RezervacijaDAO;
import com.etf.dto.Tretman;
import com.etf.exceptions.NotFoundException;
import com.etf.model.Rezervacija;
import com.etf.repository.RezervacijaRepository;
import feign.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Rezervacija")
public class RezervacijaController {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @PostMapping(path = "/")
    ResponseEntity <String> addNewRezervacija(@RequestBody @Valid RezervacijaDAO rezervacijaDAO, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            Rezervacija r = new Rezervacija();

            r.setPacijentId(rezervacijaDAO.getPacijentId());
            r.setSobaId(rezervacijaDAO.getSobaId());
            r.setKrevetId(rezervacijaDAO.getKrevetId());
            r.setDatumDolaska(rezervacijaDAO.getDatumDolaska());
            r.setDatumOdlaska(rezervacijaDAO.getDatumDolaska());

            rezervacijaRepository.save(r);

            return ResponseEntity.ok("Reservation has been added.");

        }
        else{
            throw new Exception("Operation not allowed");
        }


    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Rezervacija> getAllRezervacije(HttpServletRequest request) throws Exception {

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            return rezervacijaRepository.findAll();
        }
        else{
            throw new Exception("Operation not allowed");
        }
    }

    @PutMapping(path = "/pacijentIdById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaPacijentId(@RequestBody String pacijentId, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                Rezervacija rez = rezervacija.get();

                System.out.println(pacijentId);

                rez.setPacijentId(Integer.valueOf(pacijentId));

                rezervacijaRepository.save(rez);

                return ResponseEntity.ok("Reservation has been successfully updated.");

            }catch (NotFoundException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping(path = "sobaIdById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaSobaId(@RequestBody String sobaId, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                Rezervacija rez = rezervacija.get();

                rez.setSobaId(Integer.valueOf(sobaId));

                rezervacijaRepository.save(rez);

                return ResponseEntity.ok("Reservation has been successfully updated.");

            }catch (NotFoundException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping(path = "krevetIdById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaKrevetId(@RequestBody String krevetId, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                Rezervacija rez = rezervacija.get();

                rez.setKrevetId(Integer.valueOf(krevetId));

                rezervacijaRepository.save(rez);

                return ResponseEntity.ok("Reservation has been successfully updated.");

            }catch (NotFoundException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping(path = "DatumDolaskaById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaDatumDolaska(@RequestBody String datumDolaska, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                Rezervacija rez = rezervacija.get();

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm",Locale.ENGLISH);

                rez.setDatumDolaska(formatter.parse(datumDolaska));

                rezervacijaRepository.save(rez);

                return ResponseEntity.ok("Reservation has been successfully updated.");

            }catch (NotFoundException | ParseException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @PutMapping(path = "DatumOdlaskaById/{id}")
    public @ResponseBody ResponseEntity<String> updateRezervacijaDatumOdlaska(@RequestBody String datumOdlaska, @PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try{
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                Rezervacija rez = rezervacija.get();

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm",Locale.ENGLISH);

                rez.setDatumOdlaska(formatter.parse(datumOdlaska));

                rezervacijaRepository.save(rez);

                return ResponseEntity.ok("Reservation has been successfully updated.");

            }catch (NotFoundException | ParseException e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            throw new Exception("Operation not allowed");
        }

    }

    @DeleteMapping(path = "byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteReservation(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception{

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            try {
                Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);

                if(rezervacija.isEmpty()) throw new NotFoundException("Rezervacija sa tim id-em nije pronadjena.");

                rezervacijaRepository.deleteById(id);

                return ResponseEntity.ok("Reservation has been successfully deleted.");
            }catch (NotFoundException e)
            {
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
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


    //Poziv koji sadrzi i Nadin odgovor

    @Autowired
    private TitClient tretmanClient;

    @GetMapping(path = "komunikacijaSaTit")
    public @ResponseBody RegTitClass komunikacijaSaTit(HttpServletRequest request) throws Exception {

        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals("127.0.0.1")) {

            Iterable<Rezervacija> rezervacija = rezervacijaRepository.findAll();
            Iterable<Tretman> tretman = tretmanClient.allTretmani();

            RegTitClass regTitClass = new RegTitClass(rezervacija, tretman);

            return regTitClass;
        }
        else{
            throw new Exception("Operation not allowed");
        }
    }
}