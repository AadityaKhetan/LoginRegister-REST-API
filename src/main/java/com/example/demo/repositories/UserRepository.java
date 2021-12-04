package com.example.demo.repositories;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(Long aLong);

    Optional<User> findUsersByEmail(String email);
}
