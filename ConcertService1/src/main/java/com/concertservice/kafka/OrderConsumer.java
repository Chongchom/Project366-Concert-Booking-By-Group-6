package com.concertservice.kafka;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.concertservice.entity.Orders;
import com.concertservice.repository.OrdersRepository;
import com.concertservice.service.ConcertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {

    @Autowired
    private ConcertService concertService;
    @Autowired
    private OrdersRepository orderRepository;
    
    

    @KafkaListener(topics = "ConcertRequest", groupId = "my-consumer-group")
    public void receiveConcertRequest(String message) {
    	log.info(String.format("$$ -> Order Consumed Message Confirmed-> %s", message));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Orders order = objectMapper.readValue(message, Orders.class);
            orderRepository.save(order);
            concertService.processConcertRequest(order);
            // ทำสิ่งที่ต้องการกับ Order ที่ได้รับ
        } catch (JsonProcessingException e) {
            // การแปลง JSON เป็น Orders ไม่สำเร็จ
            e.printStackTrace();
        } 
        
    }
}