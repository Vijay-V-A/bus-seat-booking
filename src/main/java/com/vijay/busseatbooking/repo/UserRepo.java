package com.vijay.busseatbooking.repo;

import com.vijay.busseatbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    boolean existsByMobile(String mobile);
    boolean existsByUserName(String userName);
}
