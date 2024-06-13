package com.example.tit.controller;

import com.example.tit.Feign.ReservationClient;
import com.example.tit.dto.Soba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/Reservation/Soba")
public class SobaController {

    @Autowired
    private ReservationClient sobaClient;

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Soba> getAllSobe(){

        return sobaClient.getAllSobe();
    }
}
