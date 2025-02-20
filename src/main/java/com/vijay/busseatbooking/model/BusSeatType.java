package com.vijay.busseatbooking.model;

import com.vijay.busseatbooking.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BusSeatType extends BaseModel {

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.ORDINAL)
    private SeatType seatType;
    private Double price;
}
