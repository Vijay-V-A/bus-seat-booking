package com.vijay.busseatbooking.PaymentGateWay;

import com.vijay.busseatbooking.dto.PaymentGateWayResponseDTO;
import com.vijay.busseatbooking.enums.PaymentStatus;
import com.vijay.busseatbooking.enums.TransactionType;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class StripPaymentGateWay implements PaymentGateWay {
    @Override
    public PaymentGateWayResponseDTO proceedToPayment(Double amount) {
        PaymentGateWayResponseDTO paymentGateWayResponseDTO = new PaymentGateWayResponseDTO();

        paymentGateWayResponseDTO.setPaymentAmount(amount);
        paymentGateWayResponseDTO.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentGateWayResponseDTO.setPaymentDate(LocalDateTime.now());
        paymentGateWayResponseDTO.setTransactionId("TRN225312");
        paymentGateWayResponseDTO.setTransactionType(TransactionType.UPI);
        paymentGateWayResponseDTO.setCurrency("INR");
        paymentGateWayResponseDTO.setError(false);
        paymentGateWayResponseDTO.setErrorMessage("");
        return paymentGateWayResponseDTO;
    }
}
