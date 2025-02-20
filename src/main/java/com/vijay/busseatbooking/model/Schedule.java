package com.vijay.busseatbooking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vijay.busseatbooking.util.CustomLocalDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseModel {

    private String scheduleName;

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate scheduleDate;

    @ManyToMany
    @JoinTable(name = "schedule_buses", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "bus_id"))
    private List<Bus> buses;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

}
