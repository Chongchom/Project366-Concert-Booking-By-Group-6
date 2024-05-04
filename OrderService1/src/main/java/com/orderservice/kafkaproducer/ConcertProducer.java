package com.orderservice.kafkaproducer;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.entity.Orders;


import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class ConcertProducer {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ConcertProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendConcertRequest(Orders order) {
        log.info(String.format("$$ -> Producing Concert Request Message -> %s", order));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);
            kafkaTemplate.send("ConcertRequest", orderJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
