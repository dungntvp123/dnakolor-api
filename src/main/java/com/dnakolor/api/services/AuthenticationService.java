package com.dnakolor.api.services;

import com.dnakolor.api.DTOs.LoginUserDTO;
import com.dnakolor.api.DTOs.RegisterUserDTO;
import com.dnakolor.api.repository.UserRepository;
import com.dnakolor.api.models.User;
import com.dnakolor.api.response.ResponseBody;
import com.dnakolor.api.utils.ValidationUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final ValidationUtils validationUtils;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            com.dnakolor.api.utils.ValidationUtils validationUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationUtils = validationUtils;
    }

    @Transactional
    public ResponseBody signup(RegisterUserDTO input) {
        List<String> validateMessage = validationUtils.getViolationMessage(input);
        if(userRepository.findByEmail(input.getEmail()).isPresent()) {
            return new  ResponseBody("User already exists", null);
        } else if(validateMessage.isEmpty()) {
            User user = new User(input.getEmail(), input.getPassword(), input.getFullName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setFullName(input.getFullName());
            user.setStatus("ACTIVE");
            user.setRole("USER_ROLE");
            userRepository.save(user);
            return new ResponseBody("User registered successfully", user);
        } else if(validateMessage.size() > 0){
            return new ResponseBody("User registration failed", validateMessage);
        }
        return new ResponseBody("User registration failed", null);
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
