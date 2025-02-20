package com.vijay.busseatbooking.controller;


import com.vijay.busseatbooking.dto.SignInRequestDTO;
import com.vijay.busseatbooking.model.TokenResponse;
import com.vijay.busseatbooking.model.User;
import com.vijay.busseatbooking.service.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
@Tag(name = "Session Controller")
public class SessionController {


    private SessionService sessionService;

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody User data) {
        return new ResponseEntity<>(sessionService.signUp(data), HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody SignInRequestDTO credential) throws Exception {
        return new ResponseEntity<>(sessionService.signIn(credential), HttpStatus.OK);
    }

    @DeleteMapping("signout")
    public ResponseEntity<User> postUserDetail(@Valid @RequestBody User data) {
        return new ResponseEntity<>(sessionService.signOut(data), HttpStatus.OK);
    }
}
