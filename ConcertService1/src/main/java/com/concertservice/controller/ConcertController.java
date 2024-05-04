package com.concertservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concertservice.entity.Concert;
import com.concertservice.service.ConcertService;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @PostMapping("/add")
    public ResponseEntity<Concert> addConcert(@RequestBody Concert concert) {
        Concert addedConcert = concertService.addConcert(concert);
        return ResponseEntity.ok(addedConcert);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable String id, @RequestBody Concert concert) {
        Concert updatedConcert = concertService.updateConcert(id, concert);
        if (updatedConcert == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedConcert);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable String id) {
        concertService.deleteConcert(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Concert>> getAllConcerts() {
        List<Concert> concerts = concertService.getAllConcerts();
        return ResponseEntity.ok(concerts);
    }
}
