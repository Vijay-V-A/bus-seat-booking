package com.vijay.busseatbooking.PaymentGateWay;

import com.vijay.busseatbooking.dto.PaymentGateWayResponseDTO;
import com.vijay.busseatbooking.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class StripPaymentGateWay implements PaymentGateWay{
    @Override
    public PaymentGateWayResponseDTO proceedToPayment(Double amount) {
        return null;
    }
}
