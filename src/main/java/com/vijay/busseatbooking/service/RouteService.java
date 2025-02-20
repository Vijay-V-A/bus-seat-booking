package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.Route;
import com.vijay.busseatbooking.repo.RouteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RouteService {

    private RouteRepo routeRepo;

    public List<Route> getAllRoutes() {
        return routeRepo.findAll();
    }

    public Route getRouteById(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        if (!route.isPresent())
            throw new RecordNotFoundException("Route not found");

        return route.get();
    }

    public Route addRoute(Route route) {
        return routeRepo.save(route);
    }

    public Route updateRoute(Long id, Route route) {

        Optional<Route> routeById = routeRepo.findById(id);
        if(!routeById.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");

        route.setId(id);
        return routeRepo.save(route);
    }

    public void deleteRoute(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        if(!route.isPresent())
            throw new RecordNotFoundException("Route with id " + id + " not found");

        routeRepo.deleteById(id);
    }
}
