package com.vijay.busseatbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignInRequestDTO {
    @NotEmpty(message = "User Name can't be empty")
    @NotNull(message = "User Name can't be null")
    @NotBlank(message = "User Name can't be blank")
    private String userName;

    @NotEmpty(message = "Password can't be empty")
    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be blank")
    private String password;
}
