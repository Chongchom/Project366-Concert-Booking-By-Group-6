package com.catalogservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catalogservice.dto.CatalogRequest;
import com.catalogservice.dto.CatalogResponse;
import com.catalogservice.model.Catalog;
import com.catalogservice.repository.CatalogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogService {

	private final CatalogRepository catalogRepository;
	
	
	public void createCatalog(CatalogRequest catalogRequest) {
		Catalog catalog = Catalog.builder()
				.name(catalogRequest.getName())
				.description(catalogRequest.getDescription())
				.price(catalogRequest.getPrice())
				.build();
		
		catalogRepository.save(catalog);
		log.info("Catalog {} is save",catalog.getId());
	}
	
	public List<CatalogResponse> getAllCatalog() {
		List<Catalog> catalog = catalogRepository.findAll();
		
		return catalog.stream().map(this::mapToCatalogResponse).toList();
	}
	
	private CatalogResponse mapToCatalogResponse(Catalog catalog) {
		return CatalogResponse.builder()
				.id(catalog.getId())
				.name(catalog.getName())
				.description(catalog.getDescription())
				.price(catalog.getPrice())
				.build();
	}
}
