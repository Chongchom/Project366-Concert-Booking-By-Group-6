package com.concertservice.service;



import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.concertservice.entity.Concert;
import com.concertservice.entity.Orders;
import com.concertservice.kafka.OrderProducer;
import com.concertservice.repository.ConcertRepository;
import com.concertservice.repository.OrdersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConcertService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private ConcertRepository concertRepository;

    public void processConcertRequest(Orders order) {
        Concert concert = concertRepository.findByConcertName(order.getConcertName());
        if (concert != null) {
            int availableSeats = concert.getAvailableSeats();
            if (availableSeats > 0) {
                order.setStatus("Confirmed");
                orderProducer.sendOrderMessage(order);
                concert.setAvailableSeats(availableSeats - 1);
                concertRepository.save(concert);
            } else {
                order.setStatus("Cancelled");
                orderProducer.sendOrderMessage(order);
            }
        } else {
            log.error("Concert not found for concert name: {}", order.getConcertName());
        }
    }

    public Concert addConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    public Concert updateConcert(String id, Concert concert) {
    	Optional<Concert> existingConcertOptional = concertRepository.findById(id);
        if (existingConcertOptional.isPresent()) {
            Concert existingConcert = existingConcertOptional.get();
            // Set new data to existing concert
            existingConcert.setConcertName(concert.getConcertName());
            existingConcert.setAvailableSeats(concert.getAvailableSeats());
            // Update existing concert
            return concertRepository.save(existingConcert);
        } else {
            return null;
        }
    }

    public void deleteConcert(String id) {
        concertRepository.deleteById(id);
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }
}
