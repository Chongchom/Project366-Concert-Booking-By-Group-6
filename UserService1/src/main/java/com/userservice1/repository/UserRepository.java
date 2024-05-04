package com.userservice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice1.entity.Orders;
import com.userservice1.entity.User;

public interface UserRepository extends JpaRepository<User,String>
{

	User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    User save(Orders order);
}
