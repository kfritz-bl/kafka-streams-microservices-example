package com.blc.domaincrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainCrawlerApplication {

    /*
    Used to query the provided domain and collect that data.
    For each found domain, the data is sent to a Kafka Topic named web-domains and a message is displayed.
     */
    public static void main(String[] args) {
        SpringApplication.run(DomainCrawlerApplication.class, args);
    }

}
