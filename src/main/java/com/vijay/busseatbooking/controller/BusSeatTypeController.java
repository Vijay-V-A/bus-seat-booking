package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.Bus;
import com.vijay.busseatbooking.model.BusSeatType;
import com.vijay.busseatbooking.service.BusSeatTypeService;
import com.vijay.busseatbooking.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/seat-type")
@AllArgsConstructor
public class BusSeatTypeController {

    private BusSeatTypeService busSeatTypeService;

    @GetMapping
    public ResponseEntity<List<BusSeatType>> getAllBusSeatTypes() {
        return ResponseEntity.ok(busSeatTypeService.getAllBusSeatTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusSeatType> getBusSeatTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(busSeatTypeService.getBusSeatTypeById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<BusSeatType> addBus(@RequestBody BusSeatType busSeatType) {
        BusSeatType savedBusSeatType = busSeatTypeService.addBusSeatType(busSeatType);
        URI location = URI.create("/routes/" + savedBusSeatType.getId());
        return ResponseEntity.created(location).body(savedBusSeatType);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<BusSeatType> updateBus(@PathVariable Long id, @RequestBody BusSeatType busSeatType) {
        return ResponseEntity.ok(busSeatTypeService.updateBusSeatType(id, busSeatType));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busSeatTypeService.deleteBusSeatType(id);
        return ResponseEntity.noContent().build();
    }
}
