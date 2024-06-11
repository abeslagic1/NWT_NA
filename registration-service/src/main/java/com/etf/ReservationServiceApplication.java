package com.etf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.etf.exceptions.EventInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
	//commm

	@Bean
	public EventInterceptor customInterceptor() {return new EventInterceptor();}

}
