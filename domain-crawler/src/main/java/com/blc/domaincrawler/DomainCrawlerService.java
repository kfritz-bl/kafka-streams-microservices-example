package com.blc.domaincrawler;

import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainCrawlerService {

    private final KafkaTemplate<String, Domain> kafkaTemplate;
    private final String KAFKA_TOPIC = "web-domains";
    public List<Domain> domainListCap = new ArrayList<>();

    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {

        clearDomains();

        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

        domainListMono.subscribe(domainList -> domainList.domains
                .forEach(domain -> {
                    kafkaTemplate.send(KAFKA_TOPIC, domain);
                    domainListCap.add(domain);
                    System.out.println("Domain message: " + domain.getDomain());
                }));
    }

    public void crawl(String name, String zone) {

        clearDomains();

        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=" + zone)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

        domainListMono.subscribe(domainList -> domainList.domains
                .forEach(domain -> {
                    kafkaTemplate.send(KAFKA_TOPIC, domain);
                    domainListCap.add(domain);
                    System.out.println("Domain message: " + domain.getDomain());
                }));
    }

    public List<Domain> getDomains() {
        return domainListCap;
    }

    private void clearDomains() {
        domainListCap.clear();
    }
}
