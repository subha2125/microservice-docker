package com.hystrix.hystrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hystrix.hystrix.models.CatalogItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CatalogService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(groupKey = "getFallBackCatalog", commandKey = "StoreSubmission", threadPoolKey = "StoreSubmission", commandProperties = {
	        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
	        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
	        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
	        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") }, threadPoolProperties = {
	        @HystrixProperty(name = "coreSize", value = "30"),
	        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") })
	public List<CatalogItem> getCatalog(String userId) {
		return (List<CatalogItem>) restTemplate.getForObject("http://movie-catalog-service/catalog/" + userId,
				List.class);
	}

	public List<CatalogItem> getFallBackCatalog(String userId) {
		return (List<CatalogItem>) restTemplate.getForObject("http://localhost:8081/catalog/" + userId,
				List.class);
	}
}
