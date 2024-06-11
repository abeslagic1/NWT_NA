package com.etf.SystemEvents;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class SystemEventsApplication implements ApplicationContextAware {

	static ApplicationContext applicationContext;
	public static void main(String[] args) {
		SpringApplication.run(SystemEventsApplication.class, args);

		Server server = ServerBuilder.forPort(9090).addService(new SystemEventsService(applicationContext)).build();

		try {
			server.start();
			server.awaitTermination();
		}
		catch(Exception exception){}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

}
