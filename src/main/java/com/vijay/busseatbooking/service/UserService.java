package com.vijay.busseatbooking.service;

import com.vijay.busseatbooking.exception.RecordNotFoundException;
import com.vijay.busseatbooking.model.User;
import com.vijay.busseatbooking.model.UserPrinciple;
import com.vijay.busseatbooking.repo.UserRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getOneUsers(Long id) throws RecordNotFoundException {

        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty())
            throw new RecordNotFoundException("User not found: " + id);

        return user.get();
    }

    public User postUser(User data) {
        data.setPassword(encoder.encode(data.getPassword()));
        System.out.println(data.toString());
        return userRepo.save(data);
    }

    public User updateUsers(Long id, @Valid User data) {
        User user = getOneUsers(id);
        user.setUserName(data.getUserName());
        user.setPassword(encoder.encode(data.getPassword()));

        return userRepo.save(user);
    }

    public String deleteUsers(Long id) {
        User user = getOneUsers(id);
        userRepo.deleteById(user.getId());

        return "User deleted successfully";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByUserName(username);

        if(!user.isPresent()) throw new UsernameNotFoundException("User Not found");

        return new UserPrinciple(user.get());
    }
}
