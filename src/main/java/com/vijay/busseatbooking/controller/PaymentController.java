package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.Payment;
import com.vijay.busseatbooking.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@Tag(name = "Payment Controller")
public class PaymentController {

    private PaymentService paymentService;

//
//    @PostMapping
//    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
//        Payment processedPayment = paymentService.processPayment(payment);
//        URI location = URI.create("/api/v1/payments/" + processedPayment.getId());
//        return ResponseEntity.created(location).body(processedPayment);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }
}
