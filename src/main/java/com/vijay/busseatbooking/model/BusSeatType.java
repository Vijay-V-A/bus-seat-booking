package com.vijay.busseatbooking.model;

import com.vijay.busseatbooking.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class BusSeatType extends BaseModel {
    private SeatType seatType;
    private double price;
}
