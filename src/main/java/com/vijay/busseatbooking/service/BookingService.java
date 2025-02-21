package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.dto.BookingRequestDTO;
import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Booking;
import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.Payment;
import com.vijay.busseatbooking.model.Route;
import com.vijay.busseatbooking.model.Seat;
import com.vijay.busseatbooking.model.User;
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
    private UserService userService;
    private RouteService routeService;
    private BusService busService;
    private SeatService seatService;

    @Transactional
    public Booking bookSeat(BookingRequestDTO bookingRequestDTO) {
        User user = userService.getOneUsers(bookingRequestDTO.getUserId());
        Route route = routeService.getRouteById(bookingRequestDTO.getRouteId());
        Bus bus = busService.getBusById(bookingRequestDTO.getBusId());
        List<Seat> seats = seatService.getSeatsByIds(bookingRequestDTO.getSeatIds());

        Double totalAmount = seats.stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();

        Payment payment = new Payment();
        Booking booking = new Booking();

        payment.setAmount(totalAmount);
        payment.setUser(user);

        Payment successPayment = paymentService.processPayment(payment);

        booking.setUser(user);
        booking.setBus(bus);
        booking.setRoute(route);
        booking.setSeats(seats);
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
