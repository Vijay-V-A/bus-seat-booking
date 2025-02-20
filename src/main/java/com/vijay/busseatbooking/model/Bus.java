package com.vijay.busseatbooking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vijay.busseatbooking.enums.BusType;
import com.vijay.busseatbooking.util.CustomLocalDateTimeDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm a")
    private LocalDateTime startDateTime;


    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm a")
    private LocalDateTime endDateTime;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;
}
