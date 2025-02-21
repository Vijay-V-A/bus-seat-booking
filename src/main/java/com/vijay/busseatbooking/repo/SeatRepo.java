package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.Seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {
     List<Seat> findByIdIn(List<Long> ids);
}
