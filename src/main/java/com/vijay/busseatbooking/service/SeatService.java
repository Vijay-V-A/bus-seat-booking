package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Seat;
import com.vijay.busseatbooking.repo.SeatRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatService {

    private SeatRepo SeatRepo;

    public List<Seat> getAllSeats() {
        return SeatRepo.findAll();
    }

    public Seat getSeatById(Long id) {
        Optional<Seat> seat = SeatRepo.findById(id);
        if (!seat.isPresent())
            throw new RecordNotFoundException("Route not found");

        return seat.get();
    }

    public Seat addSeat(Seat route) {
        return SeatRepo.save(route);
    }

    public Seat updateSeat(Long id, Seat route) {

        Optional<Seat> routeById = SeatRepo.findById(id);
        if(!routeById.isPresent())
            throw new RecordNotFoundException("Seat with id " + id + " not found");
        route.setId(id);
        return SeatRepo.save(route);
    }

    public void deleteSeat(Long id) {
        Optional<Seat> seat = SeatRepo.findById(id);
        if(!seat.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");

        SeatRepo.deleteById(id);
    }
}

