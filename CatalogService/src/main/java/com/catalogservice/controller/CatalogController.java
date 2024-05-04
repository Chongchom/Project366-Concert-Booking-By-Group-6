package com.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.catalogservice.dto.CatalogRequest;
import com.catalogservice.dto.CatalogResponse;
import com.catalogservice.service.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

	private final CatalogService catalogService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCatalog(@RequestBody CatalogRequest catalogRequest) {
		catalogService.createCatalog(catalogRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CatalogResponse> getAllCatalog(){
		return catalogService.getAllCatalog();
	}
	
}
