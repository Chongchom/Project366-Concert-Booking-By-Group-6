package com.searchservice.repository;

import com.searchservice.model.Concert;
import com.searchservice.model.SearchCriteria;
import com.searchservice.model.SearchRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchingRepository extends JpaRepository<Concert, Integer> {


    @Query("SELECT c FROM Concert c WHERE c.artistName LIKE %:artistName%") // Example query
    List<Concert> findByArtistName(String artistName);

	List<Concert> findConcertsByCriteria(SearchCriteria criteria);
}
