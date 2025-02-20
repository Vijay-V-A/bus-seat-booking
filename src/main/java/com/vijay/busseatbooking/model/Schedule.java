package com.vijay.busseatbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseModel {

    private String scheduleName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @ManyToMany
    @JoinTable(
            name = "schedule_buses",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id")
    )
    private List<Bus> buses;

    @ManyToMany
    @JoinTable(
            name = "schedule_booking",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id")
    )
    private List<Schedule> schedules;


    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

}
