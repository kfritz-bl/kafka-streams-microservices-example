package com.blc.domainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainServiceApplication {
	
	/*
	Consumes the active domains found in the active.web-domains Kafka Topic and displays a message for each.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DomainServiceApplication.class, args);
	}
	
}
