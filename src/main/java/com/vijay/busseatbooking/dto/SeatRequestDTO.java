package com.vijay.busseatbooking.dto;

import com.vijay.busseatbooking.enums.SeatStatus;
import com.vijay.busseatbooking.enums.SeatType;
import com.vijay.busseatbooking.model.BusSeatType;
import lombok.Data;

@Data
public class SeatRequestDTO {

    private String seatNumber;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private Long busId;
}
