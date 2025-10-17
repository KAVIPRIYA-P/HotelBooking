package com.example.hotelbooking.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer roomsBooked;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    // Default constructor
    public Booking() {}

    // Parameterized constructor (optional)
    public Booking(String customerName, String customerEmail, LocalDate checkIn, LocalDate checkOut, Integer roomsBooked, Hotel hotel) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomsBooked = roomsBooked;
        this.hotel = hotel;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getRoomsBooked() {
        return roomsBooked;
    }

    public void setRoomsBooked(Integer roomsBooked) {
        this.roomsBooked = roomsBooked;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}