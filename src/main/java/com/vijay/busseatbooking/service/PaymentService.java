package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.PaymentGateWay.PaymentGateWay;
import com.vijay.busseatbooking.dto.PaymentGateWayResponseDTO;
import com.vijay.busseatbooking.model.Payment;
import com.vijay.busseatbooking.repo.PaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepo paymentRepo;
    private PaymentGateWay paymentGateWay;

    public Payment processPayment(Payment payment) {
        PaymentGateWayResponseDTO paymentGateWayResponseDTO =  paymentGateWay.proceedToPayment(payment.getAmount());
        payment.setCurrency(paymentGateWayResponseDTO.getCurrency());
        payment.setTransactionId(paymentGateWayResponseDTO.getTransactionId());
        payment.setTransactionType(paymentGateWayResponseDTO.getTransactionType());
        payment.setPaymentStatus(paymentGateWayResponseDTO.getPaymentStatus());
        payment.setAmount(paymentGateWayResponseDTO.getPaymentAmount());

        return paymentRepo.save(payment);
    }

    public Payment getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepo.findById(id);
        if (!payment.isPresent())
             throw new RuntimeException("Payment not found");

        return payment.get();
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepo.findByUserId(userId);
    }
}
