package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
    Orders save(Orders order);
}