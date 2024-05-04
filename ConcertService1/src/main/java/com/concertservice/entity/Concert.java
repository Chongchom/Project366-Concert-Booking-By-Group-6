package com.concertservice.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "concerts")
public class Concert {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "concert_id", unique = true, nullable = false, length = 36)
	private String concertId;


    @Column(name = "concert_name", unique = true)
    private String concertName;

    @Column(name = "available_seats")
    private int availableSeats;

    // Constructors, getters, and setters

    public Concert() {
        generateUUID(); 
    }
    
    public Concert(Concert concert) {
    	this.concertId = getId();
    	this.concertName = getConcertName();
        this.availableSeats = getAvailableSeats();
    }

    public Concert(String concertName, int availableSeats) {
    	this();
        this.concertName = concertName;
        this.availableSeats = availableSeats;
    }

    public String getId() {
        return concertId;
    }

    public void setId(String id) {
        this.concertId = id;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public void generateUUID() {
        this.concertId = UUID.randomUUID().toString().replaceAll("-", "");
    }
}