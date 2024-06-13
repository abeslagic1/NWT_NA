package com.example.tit.Feign;

import com.example.tit.dto.Soba;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="reservation-service", url = "http://localhost:8081")
public interface ReservationClient {

    @GetMapping(path = "Reservation/Soba/")
    public @ResponseBody Iterable<Soba> getAllSobe();
}
