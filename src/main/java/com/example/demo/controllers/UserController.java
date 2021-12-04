package com.example.demo.controllers;

import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*
       *****************
       * Endpoint to validate the fields of User entity according to the rules mentioned in entity class
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    /*
     *****************
     * POST request to register a new user
     */
    @PostMapping("/user")
    public ResponseEntity  registerUser(@RequestBody @Valid User user){
        return userService.save(user);
        //return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    /*
     *****************
     * POST request for login
     */
    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /*
     *****************
     * GET request to find a specific user with Id.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity getUserById(@PathVariable String userId){
        return userService.getUserById(Long.parseLong(userId));
    }
}
