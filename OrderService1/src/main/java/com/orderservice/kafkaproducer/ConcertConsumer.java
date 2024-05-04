package com.orderservice.kafkaproducer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.entity.Orders;
import com.orderservice.repository.OrdersRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConcertConsumer {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    @KafkaListener(topics = "ConcertResponse", groupId = "my-consumer-group")
    public void receiveConcertResponse(String message) {
        log.info(String.format("$$ -> Received Concert Response Message -> %s", message));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Orders order = objectMapper.readValue(message, Orders.class);
            if (order.getStatus().equals("Confirmed")) {
                ordersRepository.save(order);
                kafkaTemplate.send("ORDER", objectMapper.writeValueAsString(order)); 
            } else if (order.getStatus().equals("Cancelled")) {
                cancelOrder(order);
                kafkaTemplate.send("ORDER", objectMapper.writeValueAsString(order)); 
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void cancelOrder(Orders order) {
        order.setStatus("Cancelled");
        ordersRepository.save(order);
    }
}
