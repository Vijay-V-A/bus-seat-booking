package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findFirstByScheduleDateAndRoute_SourceAndRoute_Destination(LocalDate scheduleDate, String source,
            String destination);
}
