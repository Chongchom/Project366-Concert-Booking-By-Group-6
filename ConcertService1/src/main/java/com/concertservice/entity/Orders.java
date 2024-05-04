package com.concertservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {

    @Id
    @Column(name="orderid")
    private String orderId;

    @Column(name="userid")
    private String userId;

    @Column(name="concertName")
    private String concertName;

    @Column(name="price")
    private double price;

    @Column(name="zone")
    private String zone;

    @Column(name="seat")
    private String seat;

    @Column(name="date")
    private String date;

    @Column(name="status")
    private String status;
    
    // Default constructor required by JPA
    public Orders() {
    }
    
    // Constructor with all fields
    public Orders(String orderId, String userId, String concertName, double price, String zone, String seat, String date, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.concertName = concertName;
        this.price = price;
        this.zone = zone;
        this.seat = seat;
        this.date = date;
        this.status = status;
    }
    
    // Getters and setters
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}