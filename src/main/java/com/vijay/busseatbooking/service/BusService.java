package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.repo.BusRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusService {

    private BusRepo busRepo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    public Bus getBusById(Long id) {

        Optional<Bus> bus = busRepo.findById(id);
        if (!bus.isPresent())
            throw new RecordNotFoundException("Bus with id " + id + " not found");

        return bus.get();
    }

    public Bus addBus(Bus bus) {

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
}
