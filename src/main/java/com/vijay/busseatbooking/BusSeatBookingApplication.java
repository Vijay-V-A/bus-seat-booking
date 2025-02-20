package com.vijay.busseatbooking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Bus Seat Booking API",
                version = "1.0",
                description = "API documentation for Online Bus Seat Booking System"
        )
)
public class BusSeatBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusSeatBookingApplication.class, args);
    }

}
