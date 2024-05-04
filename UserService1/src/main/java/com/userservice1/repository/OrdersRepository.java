package com.userservice1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice1.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
    List<Orders> findAllByUserId(String userId);
    Orders save(Orders order);
}