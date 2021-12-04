package com.example.demo.services;

import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.domain.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity save(User user);

    ResponseEntity login(UserDTO userDTO);

    ResponseEntity getUserById(long id);
}
