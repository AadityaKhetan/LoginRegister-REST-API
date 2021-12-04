package com.example.demo.services.servicesImpl;

import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //for encoding password to prevent storing plain text
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /*
     *****************
     * Method to validate login password with user password stored in database.
     */
    public boolean checkLoginPassword(String rawPassword,String encodedPassword){
        return this.passwordEncoder.matches(rawPassword,encodedPassword);
    }

    @Override
    public ResponseEntity save(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Optional<User> doesUserExist = userRepository.findUsersByEmail(user.getEmail());//checking if user with similar email exists or not.
        if(doesUserExist.isPresent() == true) return ResponseEntity.status(HttpStatus.CONFLICT).body("Provided email already exists.");

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @Override
    public ResponseEntity login(UserDTO userDTO) {
        Optional<User> doesUserExist = userRepository.findUsersByEmail(userDTO.getEmail());

        if(doesUserExist.isPresent()){
            //email id exists in the system, so verifying password
            if(checkLoginPassword(userDTO.getPassword(), doesUserExist.get().getPassword()))
                return ResponseEntity.ok().body("Login successful");
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Provided password does not match");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @Override
    public ResponseEntity getUserById(long id) {
        Optional<User> doesUserExist = userRepository.findById(id);

        if(doesUserExist.isPresent()){
            return ResponseEntity.ok().body(doesUserExist.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }
}
