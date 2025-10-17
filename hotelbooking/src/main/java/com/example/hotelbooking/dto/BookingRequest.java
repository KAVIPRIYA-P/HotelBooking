package com.example.hotelbooking.dto;

public class BookingRequest {
  public Long hotelId;
  public String customerName;
  public String customerEmail;
  public String checkIn; // ISO yyyy-MM-dd
  public String checkOut;
  public Integer roomsBooked;
}