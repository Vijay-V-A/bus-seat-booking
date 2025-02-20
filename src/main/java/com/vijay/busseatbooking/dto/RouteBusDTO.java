package com.vijay.busseatbooking.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vijay.busseatbooking.util.CustomLocalDateDeserializer;

import lombok.Data;

@Data
public class RouteBusDTO {

     @JsonDeserialize(using = CustomLocalDateDeserializer.class)
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
     private LocalDate scheduleDate;
     private String source;
     private String destination;

}
