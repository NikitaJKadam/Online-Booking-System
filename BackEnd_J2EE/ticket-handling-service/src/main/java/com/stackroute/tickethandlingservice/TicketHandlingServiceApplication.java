package com.stackroute.tickethandlingservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableRabbit
@SpringBootApplication
public class TicketHandlingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketHandlingServiceApplication.class, args);
	}

}
