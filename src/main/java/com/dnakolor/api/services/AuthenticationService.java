package com.dnakolor.api.services;

import com.dnakolor.api.DTOs.LoginUserDTO;
import com.dnakolor.api.DTOs.RegisterUserDTO;
import com.dnakolor.api.repository.UserRepository;
import com.dnakolor.api.models.User;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(RegisterUserDTO input) {
        if(userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        } else {
            User user = new User(input.getEmail(), input.getPassword(), input.getFullName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setFullName(input.getFullName());
            user.setStatus("ACTIVE");
            user.setRole("USER_ROLE");
            return userRepository.save(user);
        }
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
