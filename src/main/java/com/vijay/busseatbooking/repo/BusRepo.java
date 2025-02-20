package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {

    List<Bus> findByRoute_Id(Long routeId);
}
