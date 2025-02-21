package com.vijay.busseatbooking.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookingRequestDTO {
     private Long busId;
     private Long routeId;
     private List<Long> seatIds;
     private Long userId;

}
