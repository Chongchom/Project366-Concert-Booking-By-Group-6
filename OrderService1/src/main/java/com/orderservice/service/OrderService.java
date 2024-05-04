package com.orderservice.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.entity.Orders;
import com.orderservice.kafkaproducer.ConcertProducer;
import com.orderservice.repository.OrdersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ConcertProducer concertProducer;

    public Orders createOrder(Orders order) {
        order.generateUUID(); // สร้าง UUID สำหรับ order id
        order.setStatus("Requesting for seat");
        ordersRepository.save(order);

        // ส่งคำสั่งไปยัง ConcertProducer เพื่อตรวจสอบที่นั่งว่างที่ ConcertService
        concertProducer.sendConcertRequest(order);

        return order;
    }
    
    public Orders getOrderById(String id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @KafkaListener(topics = "ConcertResponse", groupId = "my-consumer-group")
    public void receiveConcertResponse(String message) {
        log.info(String.format("$$ -> Received Concert Response Message -> %s", message));
        // แปลงข้อความ JSON เป็นอ็อบเจกต์ Order
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Orders order = objectMapper.readValue(message, Orders.class);
            // ตรวจสอบสถานะของคำสั่งซื้อ
            if (order.getStatus().equals("Confirmed")) {
                // ถ้าสถานะเป็น Confirmed ให้อัพเดตคำสั่งซื้อในฐานข้อมูล
                ordersRepository.save(order);
                // และส่งคำสั่งไปยัง UserService ผ่าน OrderProducer
                // โดยไม่ต้องรอการตอบรับจาก UserService
                // orderProducer.sendOrder(order);
            } else if (order.getStatus().equals("Cancelled")) {
                // ถ้าสถานะเป็น Cancelled ให้ยกเลิกคำสั่งซื้อ
                cancelOrder(order);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    public Orders updateOrder(Orders order) {
        if (order.getOrderId() == null) {
            // หากไม่มี ID ให้ return null เพื่อแสดงว่าไม่สามารถอัปเดตได้
            return null;
        }
        // กำหนดค่าใหม่ให้กับคำสั่งซื้อ
        Orders existingOrder = ordersRepository.findById(order.getOrderId()).orElse(null);
        if (existingOrder == null) {
            // ถ้าไม่พบคำสั่งซื้อที่ต้องการอัปเดต ให้ return null เช่นกัน
            return null;
        }
        existingOrder.setUserId(order.getUserId());
        existingOrder.setConcertName(order.getConcertName());
        existingOrder.setPrice(order.getPrice());
        existingOrder.setZone(order.getZone());
        existingOrder.setSeat(order.getSeat());
        existingOrder.setDate(order.getDate());
        existingOrder.setStatus(order.getStatus());
        return ordersRepository.save(existingOrder);
    }

    private void cancelOrder(Orders order) {
        // ยกเลิกคำสั่งซื้อโดยอัพเดตสถานะในฐานข้อมูล
        order.setStatus("Cancelled");
        ordersRepository.save(order);
    }
    
    public void deleteOrder(String id) {
        ordersRepository.deleteById(id);
    }
    
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
