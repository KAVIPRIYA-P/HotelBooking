package com.example.hotelbooking.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
  private final HotelService service;
  public HotelController(HotelService service){ this.service = service; }

  @GetMapping
  public List<Hotel> getAll() { return service.listAll(); }

  @PostMapping
  public Hotel create(@RequestBody Hotel hotel){
    return service.save(hotel);
  }
}
