package com.example.tit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.tit.exception.EventInterceptor;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitApplication.class, args);
	}

	@Bean
	public EventInterceptor customInterceptor() {return new EventInterceptor();}


}
