package com.vijay.busseatbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Booking extends BaseModel {

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne
    private Bus bus;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne
    private Payment payment;
}
