package com.vijay.busseatbooking.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TokenResponse {

    private String token;
    private String tokenType;
    private Date createdAt;
    private Date expiredAt;
}

