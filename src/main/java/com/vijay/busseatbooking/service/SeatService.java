package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.dto.SeatRequestDTO;
import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.BusSeatType;
import com.vijay.busseatbooking.model.Seat;
import com.vijay.busseatbooking.repo.SeatRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatService {

    private SeatRepo SeatRepo;
    private BusService busService;
    private BusSeatTypeService busSeatTypeService;

    public List<Seat> getAllSeats() {
        return SeatRepo.findAll();
    }

    public Seat getSeatById(Long id) {
        Optional<Seat> seat = SeatRepo.findById(id);
        if (!seat.isPresent())
            throw new RecordNotFoundException("Route not found");

        return seat.get();
    }

    @Transactional
    public Seat addSeat(SeatRequestDTO seatRequestDTO) {
        BusSeatType busSeatType = busSeatTypeService.findBySeatType(seatRequestDTO.getSeatType());
        Bus bus = busService.getBusById(seatRequestDTO.getBusId());

        Seat seat = new Seat();
        seat.setSeatNumber(seatRequestDTO.getSeatNumber());
        seat.setBus(bus);
        seat.setSeatType(busSeatType);

        return SeatRepo.save(seat);
    }

    public Seat updateSeat(Long id, Seat route) {

        Optional<Seat> routeById = SeatRepo.findById(id);
        if (!routeById.isPresent())
            throw new RecordNotFoundException("Seat with id " + id + " not found");
        route.setId(id);
        return SeatRepo.save(route);
    }

    public void deleteSeat(Long id) {
        Optional<Seat> seat = SeatRepo.findById(id);
        if (!seat.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");

        SeatRepo.deleteById(id);
    }

    public List<Seat> getSeatsByIds(List<Long> seatIds) {
        return SeatRepo.findByIdIn(seatIds);
    }

}
