package com.searchservice.service;

import com.searchservice.model.Concert;
import com.searchservice.model.SearchCriteria;
import com.searchservice.model.SearchRequest;
import com.searchservice.model.SearchResponse;
import com.searchservice.repository.SearchingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class SearchingService {

    @Autowired
    private SearchingRepository searchingRepository;

    public SearchResponse searchConcerts(SearchRequest searchRequest) {
        SearchResponse searchResponse = new SearchResponse();

        // Use SearchCriteria for flexible search
        SearchCriteria criteria = new SearchCriteria();
        criteria.setArtistName(searchRequest.getArtistName());
        criteria.setVenueName(searchRequest.getVenueName());
        criteria.setDateRange(searchRequest.getDateRange());
        criteria.setMaxPrice(searchRequest.getMaxPrice());
        criteria.setLowPrice(searchRequest.getlowPrice());

        // Retrieve concerts based on search criteria
        List<Concert> concerts = searchingRepository.findConcertsByCriteria(criteria); // Assuming method exists

        // Set search response properties
        searchResponse.setConcerts(concerts);
        searchResponse.setTotalResults(concerts.size()); // Assuming no pagination

        return searchResponse;
    }
}
