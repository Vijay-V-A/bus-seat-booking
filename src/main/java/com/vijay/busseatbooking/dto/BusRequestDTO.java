package com.vijay.busseatbooking.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vijay.busseatbooking.enums.BusType;
import com.vijay.busseatbooking.util.CustomLocalDateTimeDeserializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BusRequestDTO {

    private String busName;
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

    private String source;
    private String destination;
}
