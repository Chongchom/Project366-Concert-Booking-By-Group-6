package com.userservice1.kafkaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice1.entity.Orders;
import com.userservice1.entity.User;
import com.userservice1.exception.ResourceNotFoundException;
import com.userservice1.repository.OrdersRepository;
import com.userservice1.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OrdersRepository orderRepository;

    public OrderConsumer(KafkaTemplate<String, String> kafkaTemplate, OrdersRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "ORDER", groupId = "my-consumer-group")
    public void consumeOrderMessage(String message) {
        log.info(String.format("$$ -> Order Consumed Message Confirmed-> %s", message));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Orders order = objectMapper.readValue(message, Orders.class);
            orderRepository.save(order);
            // ทำสิ่งที่ต้องการกับ Order ที่ได้รับ
        } catch (JsonProcessingException e) {
            // การแปลง JSON เป็น Orders ไม่สำเร็จ
            e.printStackTrace();
        } 
    }
}
