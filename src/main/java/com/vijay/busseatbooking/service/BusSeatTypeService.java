package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.enums.SeatType;
import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.BusSeatType;
import com.vijay.busseatbooking.repo.BusSeatTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusSeatTypeService {

    private BusSeatTypeRepo busSeatTypeRepo;

    public List<BusSeatType> getAllBusSeatTypes() {
        return busSeatTypeRepo.findAll();
    }

    public BusSeatType getBusSeatTypeById(Long id) {

        Optional<BusSeatType> busSeatType = busSeatTypeRepo.findById(id);
        if (!busSeatType.isPresent())
            throw new RecordNotFoundException("Bus Seat Type with id " + id + " not found");

        return busSeatType.get();
    }

    public BusSeatType addBusSeatType(BusSeatType busSeatType) {

        return busSeatTypeRepo.save(busSeatType);
    }


    public BusSeatType updateBusSeatType(Long id, BusSeatType busSeatType) {
        Optional<BusSeatType> busRepoById = busSeatTypeRepo.findById(id);
        if (!busRepoById.isPresent())
            throw new RecordNotFoundException("Bus with id " + id + " not found");

        busSeatType.setId(id);
        return busSeatTypeRepo.save(busSeatType);
    }

    public void deleteBusSeatType(Long id) {
        Optional<BusSeatType> busSeatType = busSeatTypeRepo.findById(id);
        if (!busSeatType.isPresent())
            throw new RecordNotFoundException("Bus with id " + id + " not found");

        busSeatTypeRepo.deleteById(id);
    }

    public BusSeatType findBySeatType(SeatType seatType) {
        Optional<BusSeatType> busSeatType = busSeatTypeRepo.findBySeatType(seatType);

        if(!busSeatType.isPresent())
            throw new RecordNotFoundException("Bus Seat Type with id " + seatType + " not found");

        return busSeatType.get();

    }
}
