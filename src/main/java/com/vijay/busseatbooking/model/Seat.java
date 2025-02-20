package com.vijay.busseatbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vijay.busseatbooking.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Seat extends BaseModel {
    @Column(nullable = false, unique = true)
    private String seatNumber;

    @ManyToOne
    private BusSeatType seatType;

    @Enumerated(value = EnumType.ORDINAL)
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @JsonIgnoreProperties("seats")
    private Bus bus;
}
