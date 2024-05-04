package com.userservice1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice1.entity.Orders;
import com.userservice1.entity.User;
import com.userservice1.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value= "/getusers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getusers/id={id}")
    public ResponseEntity<User> getUserById(@PathVariable(value="id") String id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable String userId) {
        List<Orders> orders = userService.getOrdersByUserId(userId);
        if (orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    

    @GetMapping("/getusers/username={username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(value="username")String username) {
        try {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getusers/email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value="email")String email) {
        try {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return"Add User Success";
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") String id, @RequestBody User user) {
        try {
            User existUser = userService.getUserById(id);
            if (existUser != null) {
                existUser.setUsername(user.getUsername());
                existUser.setFname(user.getFname());
                existUser.setLname(user.getLname());
                existUser.setEmail(user.getEmail());
                existUser.setPhone(user.getPhone());
                existUser.setRole(user.getRole());
                User updatedUser = userService.updateUser(existUser);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public AddResponse deleteUser(@PathVariable(value="id") String id) {
        return userService.deleteUser(id);
    }
}