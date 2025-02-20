package com.vijay.busseatbooking.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vijay.busseatbooking.dto.BusRequestDTO;
import com.vijay.busseatbooking.enums.BusType;
import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.Route;
import com.vijay.busseatbooking.repo.BusRepo;
import com.vijay.busseatbooking.util.CustomLocalDateTimeDeserializer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusService {

    private BusRepo busRepo;
    private RouteService routeService;


    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    public Bus getBusById(Long id) {

        Optional<Bus> bus = busRepo.findById(id);
        if (!bus.isPresent())
            throw new RecordNotFoundException("Bus with id " + id + " not found");

        return bus.get();
    }

    public Bus addBus(BusRequestDTO busRequestDTO) {
        Bus bus = new Bus();
        Route route = routeService.findbyRouteSourceAndDestination(busRequestDTO.getSource(), busRequestDTO.getDestination());
        bus.setBusName(busRequestDTO.getBusName());
        bus.setBusType(busRequestDTO.getBusType());
        bus.setBusNumber(busRequestDTO.getBusNumber());
        bus.setDriverName(busRequestDTO.getDriverName());
        bus.setMobile(busRequestDTO.getMobile());
        bus.setTotalSeats(busRequestDTO.getTotalSeats());
        bus.setTotalAvailableSeats(busRequestDTO.getTotalAvailableSeats());
        bus.setStartDateTime(busRequestDTO.getStartDateTime());
        bus.setEndDateTime(busRequestDTO.getEndDateTime());
        bus.setRoute(route);

        return busRepo.save(bus);
    }


    public Bus updateBus(Long id, Bus bus) {
        Optional<Bus> busRepoById = busRepo.findById(id);
        if (!busRepoById.isPresent())
            throw new RecordNotFoundException("Bus with id " + id + " not found");

        bus.setId(id);
        return busRepo.save(bus);
    }

    public void deleteBus(Long id) {
        Optional<Bus> bus = busRepo.findById(id);
        if (!bus.isPresent())
             throw new RecordNotFoundException("Bus with id " + id + " not found");

        busRepo.deleteById(id);
    }

    public List<Bus> findAllBusesByRoute(Long id) {
        return busRepo.findByRoute_Id(id);
    }
}
