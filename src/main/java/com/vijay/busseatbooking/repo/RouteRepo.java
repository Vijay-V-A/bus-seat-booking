package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.Route;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {

    Optional<Route> findBySourceAndDestination(String source, String destination);
}
