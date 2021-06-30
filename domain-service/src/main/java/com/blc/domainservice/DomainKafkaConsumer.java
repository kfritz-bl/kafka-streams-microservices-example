package com.blc.domainservice;

import com.blc.domaincrawler.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

    @Bean
    public Consumer<KStream<String, Domain>> domainService() {
        return kStream -> kStream.foreach((key, domain) ->
                System.out.printf("Domain consumed[%s] Status[%s]%n",
                        domain.getDomain(),
                        (domain.isDead() ? "dead" : "alive")));
    }
}
