package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.dto.ScheduleRequestDTO;
import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.Route;
import com.vijay.busseatbooking.model.Schedule;
import com.vijay.busseatbooking.repo.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepo scheduleRepo;
    private RouteService routeService;
    private BusService busService;

    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    @Transactional
    public Schedule addSchedule(ScheduleRequestDTO scheduleRequestDTO) {

        Route route = routeService.findbyRouteSourceAndDestination(scheduleRequestDTO.getSource(),
                scheduleRequestDTO.getDestination());
        List<Bus> buses = busService.findAllBusesByRoute(route.getId());

        Schedule schedule = new Schedule();

        schedule.setScheduleName(scheduleRequestDTO.getScheduleName());
        schedule.setScheduleDate(scheduleRequestDTO.getScheduleDate());
        schedule.setRoute(route);
        schedule.setBuses(buses);

        return scheduleRepo.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule schedule) {

        Optional<Schedule> scheduleById = scheduleRepo.findById(id);
        if (!scheduleById.isPresent())
            throw new RecordNotFoundException("Schedule with id " + id + " not found");
        schedule.setId(id);

        return scheduleRepo.save(schedule);
    }

    public void deleteSchedule(Long id) {

        Optional<Schedule> seat = scheduleRepo.findById(id);
        if (!seat.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");
        scheduleRepo.deleteById(id);
    }

    public List<Bus> getBusesByScheduleDateAndRoute(LocalDate scheduleDate, String source, String destination) {
        Optional<Schedule> schedule = scheduleRepo
                .findFirstByScheduleDateAndRoute_SourceAndRoute_Destination(scheduleDate, source, destination);
        if (!schedule.isPresent())
            throw new RecordNotFoundException("No buses found for this route");

        return schedule.get().getBuses();

    }

}
