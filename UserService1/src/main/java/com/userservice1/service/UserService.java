package com.userservice1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice1.controller.AddResponse;
import com.userservice1.entity.Orders;
import com.userservice1.entity.User;
import com.userservice1.repository.OrdersRepository;
import com.userservice1.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User addUser(User user) {
        // Set the ID manually
        user.generateUUID(); // สร้าง UUID สำหรับ id ของผู้ใช้
        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<Orders> getOrdersByUserId(String userId) {
        return ordersRepository.findByUserId(userId);
    }

    public List<Orders> getAllOrderByUserId(String userId) {
        return ordersRepository.findAllByUserId(userId);
    }

    public AddResponse deleteUser(String id) {
        userRepository.deleteById(id);

        AddResponse res = new AddResponse();
        res.setMessage("User Deleted !");
        res.setId(id);
        return res;
    }
}
