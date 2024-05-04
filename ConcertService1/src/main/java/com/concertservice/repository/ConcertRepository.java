package com.concertservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concertservice.entity.Concert;



public interface ConcertRepository extends JpaRepository<Concert, String> {
    Concert findByConcertName(String concertName);
    Concert save(Concert concert);
}