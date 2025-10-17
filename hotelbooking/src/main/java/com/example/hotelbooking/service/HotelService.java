package com.example.hotelbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.HotelRepository;

@Service
public class HotelService {
  private final HotelRepository repo;
  public HotelService(HotelRepository repo){ this.repo = repo; }

  public List<Hotel> listAll(){ return repo.findAll(); }

  public Hotel save(Hotel h){ return repo.save(h); }

  public Hotel findById(Long id){ return repo.findById(id).orElse(null); }
}
