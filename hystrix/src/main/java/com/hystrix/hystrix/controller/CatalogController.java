package com.hystrix.hystrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hystrix.hystrix.models.CatalogItem;
import com.hystrix.hystrix.service.CatalogService;

@RestController
@RequestMapping("/hystrixcatalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;
	
	@CrossOrigin("*")
	@GetMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){
		return catalogService.getCatalog(userId);
	} 
}
