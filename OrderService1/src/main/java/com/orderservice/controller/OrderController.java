package com.orderservice.controller;

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


import com.orderservice.entity.Orders;
import com.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;
	
	@GetMapping("/getAll")
    public ResponseEntity<List<Orders>> getAllConcerts() {
        List<Orders> concerts = orderService.getAllOrders();
        return ResponseEntity.ok(concerts);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Orders> createOrder(@PathVariable String userId, @RequestBody Orders order) {
        // กำหนด userId ให้กับคำสั่งซื้อจาก Path
        order.setUserId(userId);
        
        // เรียกใช้งาน OrderService เพื่อสร้างคำสั่งซื้อ
        Orders createdOrder = orderService.createOrder(order);
        
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable String id) {
        Orders order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable String id, @RequestBody Orders order) {
        Orders existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }
        // Set the ID of the incoming order to match the existing order ID
        order.setOrderId(id);
        Orders updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}