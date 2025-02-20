package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.Route;
import com.vijay.busseatbooking.service.RouteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/route")
@AllArgsConstructor
@Tag(name = "Route Controller")
public class RouteController {

    private RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
         return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        Route savedRoute = routeService.addRoute(route);
        URI location = URI.create("/routes/" + savedRoute.getId());
        return ResponseEntity.created(location).body(savedRoute);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.updateRoute(id, route));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
