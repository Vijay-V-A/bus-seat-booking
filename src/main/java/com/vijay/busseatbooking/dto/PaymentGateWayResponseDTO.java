package com.vijay.busseatbooking.dto;

import com.vijay.busseatbooking.enums.PaymentStatus;
import com.vijay.busseatbooking.enums.TransactionType;
import lombok.Data;

@Data
public class PaymentGateWayResponseDTO {

    private Double paymentAmount;
    private PaymentStatus paymentStatus;
    private String paymentDate;
    private String transactionId;
    private TransactionType transactionType;
    private String currency;
    private Boolean error;
    private String errorMessage;
}
