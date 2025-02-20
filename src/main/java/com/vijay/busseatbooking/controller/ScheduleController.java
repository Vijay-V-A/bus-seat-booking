package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.Schedule;
import com.vijay.busseatbooking.service.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/schedules")
@AllArgsConstructor
@Tag(name = "Schedule Controller")
public class ScheduleController {

    private ScheduleService scheduleService;


    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {

        Schedule savedSchedule = scheduleService.addSchedule(schedule);
        URI location = URI.create("/routes/" + savedSchedule.getId());
        return ResponseEntity.created(location).body(savedSchedule);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, schedule));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<Schedule>> getSchedulesBySourceAndDestination(
            @RequestParam String source,
            @RequestParam String destination) {
        return ResponseEntity.ok(scheduleService.getSchedulesBySourceAndDestination(source, destination));
    }

}
