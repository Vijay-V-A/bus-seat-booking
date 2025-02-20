package com.vijay.busseatbooking.PaymentGateWay;

import com.vijay.busseatbooking.dto.PaymentGateWayResponseDTO;
import com.vijay.busseatbooking.model.Payment;

public interface PaymentGateWay {
    public PaymentGateWayResponseDTO proceedToPayment(Double amount);
}
