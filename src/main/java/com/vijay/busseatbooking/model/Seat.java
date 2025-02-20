package com.vijay.busseatbooking.model;

import com.vijay.busseatbooking.enums.SeatStatus;
import com.vijay.busseatbooking.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Seat extends BaseModel{
    private String seatNumber;

    @ManyToOne
    private BusSeatType seatType;

    private SeatStatus seatStatus;
}
