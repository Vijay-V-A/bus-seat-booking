package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Schedule;
import com.vijay.busseatbooking.repo.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepo scheduleRepo;


    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }



    public Schedule updateSchedule(Long id, Schedule schedule) {

        Optional<Schedule> scheduleById = scheduleRepo.findById(id);
        if(!scheduleById.isPresent())
            throw new RecordNotFoundException("Schedule with id " + id + " not found");
        schedule.setId(id);

        return scheduleRepo.save(schedule);
    }

    public void deleteSchedule(Long id) {

        Optional<Schedule> seat = scheduleRepo.findById(id);
        if(!seat.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");
        scheduleRepo.deleteById(id);
    }

    public List<Schedule> getSchedulesBySourceAndDestination(String source, String destination) {
        return scheduleRepo.findByRoute_SourceAndRoute_DestinationAndStartDateTimeAfter(source, destination, LocalDateTime.now());
    }

}
