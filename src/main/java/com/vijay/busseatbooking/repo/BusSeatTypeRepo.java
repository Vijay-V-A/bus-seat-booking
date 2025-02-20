package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.enums.SeatType;
import com.vijay.busseatbooking.model.BusSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusSeatTypeRepo extends JpaRepository<BusSeatType, Long> {
    Optional<BusSeatType> findBySeatType(SeatType seatType);
}
