package com.searchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.searchservice.exceptions.ResourceNotFoundException;
import com.searchservice.model.SearchRequest; 
import com.searchservice.model.SearchResponse; 
import com.searchservice.service.SearchingService; 

@RestController
public class SearchController {

    @Autowired
    private SearchingService searchingService;

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> searchConcerts(@RequestBody SearchRequest searchRequest) throws ResourceNotFoundException{
        SearchResponse searchResponse = searchingService.searchConcerts(searchRequest);
        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }
}






