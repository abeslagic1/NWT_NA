package com.etf.controller;

import com.etf.Feign.TitClient;
import com.etf.dto.Tretman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
