package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findByRoute_SourceAndRoute_DestinationAndStartDateTimeAfter(String source, String destination, LocalDateTime dateTime);

}
