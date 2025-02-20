package com.vijay.busseatbooking.model;

import com.vijay.busseatbooking.enums.PaymentStatus;
import com.vijay.busseatbooking.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseModel{
    private String transactionId;
    private Double amount;
    private String currency;
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}
