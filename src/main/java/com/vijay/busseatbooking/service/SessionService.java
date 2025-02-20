package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.dto.SignInRequestDTO;
import com.vijay.busseatbooking.enums.UserRole;
import com.vijay.busseatbooking.exception.RecordExistException;
import com.vijay.busseatbooking.model.TokenResponse;
import com.vijay.busseatbooking.model.User;
import com.vijay.busseatbooking.repo.UserRepo;
import com.vijay.busseatbooking.security.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {


    private UserRepo userRepo;
    private  JWTService jwtService;
    private AuthenticationManager authManager;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public User signUp(User data) {
        data.setPassword(encoder.encode(data.getPassword()));
        if(data.getRole() == null)
             data.setRole(UserRole.ROLE_USER);
        if (userRepo.existsByMobile(data.getMobile()) || userRepo.existsByUserName(data.getUserName()))
            throw new RecordExistException("User already exists");

        return userRepo.save(data);
    }

    public TokenResponse signIn(SignInRequestDTO credential) throws Exception {
        System.out.println(credential.getUserName());
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getUserName(), credential.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(credential);
        else throw new Exception("UserName or Password is incorrect");
    }

    public User signOut(@Valid User data) {
        return null;
    }
}
