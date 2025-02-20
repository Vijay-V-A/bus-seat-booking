package com.vijay.busseatbooking.model;

import com.vijay.busseatbooking.enums.BusType;
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
public class Bus extends BaseModel {

    private String busName;
    @Enumerated(value = EnumType.ORDINAL)
    private BusType busType;
    private String busNumber;
    private String driverName;
    private String mobile;
    private Integer totalSeats;
    private Integer totalAvailableSeats;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;
}
