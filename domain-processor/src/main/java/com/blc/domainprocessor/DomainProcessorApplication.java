package com.blc.domainprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainProcessorApplication {
	
	/*
	Consumes all of the messages that are coming into the web-domains Kafka Topic.
	The service will then use Kafka Streams to filter out some of the messages based on desired type (active/inactive)
	and display a message for each.
	The processor will then publish the good messages to the active.web-domains Kafka Topic.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DomainProcessorApplication.class, args);
	}
}
