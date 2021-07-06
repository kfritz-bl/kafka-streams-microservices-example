package com.blc.domainprocessor;

import com.blc.domaincrawler.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {
	
	@Bean
	public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
		return kStream -> kStream.filter((key, domain) -> {
			System.out.println((domain.isDead() ? "Inactive " : "Active ") + "Domain: " + domain.getDomain());
			return !domain.isDead();
		});
	}
}
