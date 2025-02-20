package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.dto.SeatRequestDTO;
import com.vijay.busseatbooking.model.Seat;
import com.vijay.busseatbooking.service.SeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/seats")
@AllArgsConstructor
@Tag(name = "Seat Controller")
public class SeatController {

    private SeatService seatService;

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getSeatById(id));

    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Seat> addSeat(@RequestBody SeatRequestDTO seatRequestDTO) {

        Seat savedSeat = seatService.addSeat(seatRequestDTO);
        URI location = URI.create("/routes/" + savedSeat.getId());
        return ResponseEntity.created(location).body(savedSeat);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.updateSeat(id, seat));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}
