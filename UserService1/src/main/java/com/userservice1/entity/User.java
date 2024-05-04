package com.userservice1.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;

@Entity
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "userid", unique = true, nullable = false)
	private String id;
    
    @Column(name="username")
    String username;
    
    @Column(name="lname")
    String lname;
    
    @Column(name="phone")
    String phone;
    
    @Column(name="role")
    String role;
    
    @Column(name="fname")
    String fname;
    
    @Column(name="email")
    String email;
    
    @Transient
    @OneToMany(mappedBy = "user")
    private List<Orders> order = new ArrayList<>();
    
    
    public User() {
        generateUUID(); // เรียกเมธอด generateUUID() เพื่อกำหนดค่า id
    }
    
    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.order = user.getOrders();
    }
    
    public User(String username, String fname, String lname, String email, String phone, String role) {
        this(); // เรียกใช้งานคอนสตรักเตอร์ที่ไม่มีพารามิเตอร์เพื่อเรียก generateUUID()
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getLname() {
        return lname;
    }
    
    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getFname() {
        return fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Orders> getOrders() {
        return order;
    }

    public void setOrders(List<Orders> orders) {
        this.order = orders;
    }
    
    public void addOrder(Orders order) {
        this.order.add(order);
    }
    
 // เมธอด generateUUID() สำหรับสร้างค่า id โดยใช้ UUID
    public void generateUUID() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

}
