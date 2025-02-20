package com.vijay.busseatbooking.controller;

import com.vijay.busseatbooking.model.User;
import com.vijay.busseatbooking.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@Tag(name = "User Controller")
public class UserController {

    private UserService userService;

    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getOneUsers(id), HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<User> postUser(@Valid @RequestBody User data) {
        System.out.println(data.toString());
        return new ResponseEntity<>(userService.postUser(data), HttpStatus.CREATED);
    }

    @PatchMapping("user/{id}")
    public ResponseEntity<User> updateUsers(@PathVariable Long id, @RequestBody User data) {
        return new ResponseEntity<>(userService.updateUsers(id,data), HttpStatus.OK);
    }

    @DeleteMapping("User/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUsers(id), HttpStatus.OK);
    }
}
