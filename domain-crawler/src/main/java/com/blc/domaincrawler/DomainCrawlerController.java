package com.blc.domaincrawler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {
	
	private final DomainCrawlerService domainCrawlerService;
	
	public DomainCrawlerController(DomainCrawlerService domainCrawlerService) {
		this.domainCrawlerService = domainCrawlerService;
	}
	
	@GetMapping
	public List<Domain> getDomains() {
		return this.domainCrawlerService.getDomains();
	}
	
	@GetMapping("/lookup/{name}")
	public String lookup(@PathVariable("name") final String name) {
		domainCrawlerService.crawl(name);
		return "Domain crawler has scrapped your data";
	}
	
	@GetMapping("/lookup/{name}/{zone}")
	public String lookup(@PathVariable("name") final String name, @PathVariable("zone") final String zone) {
		domainCrawlerService.crawl(name, zone);
		return "Domain crawler has scrapped your data";
	}
}
