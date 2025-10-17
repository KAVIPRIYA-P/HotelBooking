package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
