package com.example.hotelbooking.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelbooking.dto.BookingRequest;
import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.HotelRepository;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private final BookingRepository bookingRepo;
  private final HotelRepository hotelRepo;

  public BookingController(BookingRepository bookingRepo, HotelRepository hotelRepo){
    this.bookingRepo = bookingRepo;
    this.hotelRepo = hotelRepo;
  }

  @PostMapping
  public ResponseEntity<?> createBooking(@RequestBody BookingRequest req){
    Hotel hotel = hotelRepo.findById(req.hotelId).orElse(null);
    if(hotel == null) return ResponseEntity.badRequest().body("Hotel not found");
    if(req.roomsBooked == null || req.roomsBooked <= 0) return ResponseEntity.badRequest().body("Invalid rooms");

    if(hotel.getAvailableRooms() < req.roomsBooked){
      return ResponseEntity.badRequest().body("Not enough rooms available");
    }

    Booking booking = new Booking();
    booking.setCustomerName(req.customerName);
    booking.setCustomerEmail(req.customerEmail);
    booking.setCheckIn(LocalDate.parse(req.checkIn));
    booking.setCheckOut(LocalDate.parse(req.checkOut));
    booking.setRoomsBooked(req.roomsBooked);
    booking.setHotel(hotel);

    bookingRepo.save(booking);

    // reduce availability (simple logic)
    hotel.setAvailableRooms(hotel.getAvailableRooms() - req.roomsBooked);
    hotelRepo.save(hotel);

    return ResponseEntity.ok(booking);
  }

  @GetMapping
  public ResponseEntity<?> listBookings(){ return ResponseEntity.ok(bookingRepo.findAll()); }
}