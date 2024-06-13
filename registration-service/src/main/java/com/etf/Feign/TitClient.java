package com.etf.Feign;

import com.etf.dto.Tretman;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="tit-service", url = "http://localhost:8082")
public interface TitClient {

    @GetMapping("/tit/tretmani/")
    Iterable<Tretman> allTretmani();
}
