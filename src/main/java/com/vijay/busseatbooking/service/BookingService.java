package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Booking;
import com.vijay.busseatbooking.model.Payment;
import com.vijay.busseatbooking.repo.BookingRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepo bookingRepo;
    private PaymentService paymentService;

    @Transactional
    public Booking bookSeat(Booking booking) {
        Double totalAmount = booking.getSeats().stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();

        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setUser(booking.getUser());

        Payment successPayment = paymentService.processPayment(payment);
        booking.setPayment(successPayment);
        return bookingRepo.save(booking);
    }

    public Booking getBookingById(Long id) {

        Optional<Booking> booking = bookingRepo.findById(id);
        if (!booking.isPresent())
            throw new RecordNotFoundException("Booking not found with id: " + id);

        return booking.get();

    }

    public void cancelBooking(Long id) {
        if (!bookingRepo.existsById(id))
            throw new RecordNotFoundException("Booking not found with id: " + id);

        bookingRepo.deleteById(id);
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepo.findByUserId(userId);
    }
}
