package com.orderservice.kafkaproducer;


import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.entity.Orders;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderMessage(Orders order) {
        try {
            // แปลง Object ให้เป็น JSON String
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);
            // ส่งข้อความไปยัง Kafka Topic ชื่อ "ORDER"
            kafkaTemplate.send("ORDER", orderJson);
            // ประกาศว่าส่งข้อมูลสำเร็จ
            log.info("Order message sent successfully: {}", orderJson);
        } catch (JsonProcessingException e) {
            // การแปลง Object เป็น JSON ไม่สำเร็จ
            log.error("Error converting order object to JSON: {}", e.getMessage());
        }
    }
}
