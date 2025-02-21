package com.vijay.busseatbooking.dto;

import java.time.LocalDateTime;

import com.vijay.busseatbooking.enums.PaymentStatus;
import com.vijay.busseatbooking.enums.TransactionType;
import lombok.Data;

@Data
public class PaymentGateWayResponseDTO {

    private Double paymentAmount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private String transactionId;
    private TransactionType transactionType;
    private String currency;
    private Boolean error;
    private String errorMessage;
}
