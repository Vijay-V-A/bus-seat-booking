package com.vijay.busseatbooking.PaymentGateWay;

import com.vijay.busseatbooking.dto.PaymentGateWayResponseDTO;

public interface PaymentGateWay {
    public PaymentGateWayResponseDTO proceedToPayment(Double amount);
}
