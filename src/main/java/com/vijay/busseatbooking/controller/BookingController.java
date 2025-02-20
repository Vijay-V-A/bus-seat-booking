package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.Booking;
import com.vijay.busseatbooking.model.Schedule;
import com.vijay.busseatbooking.service.BookingService;
import com.vijay.busseatbooking.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
@AllArgsConstructor
@Tag(name = "Booking Controller")
public class BookingController {

    private BookingService bookingService;
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Booking> bookSeat(@RequestBody Booking booking) {

        Booking savedBooking = bookingService.bookSeat(booking);
        URI location = URI.create("/routes/" + savedBooking.getId());
        return ResponseEntity.created(location).body(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }
}
