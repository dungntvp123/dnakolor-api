package com.dnakolor.api.Controllers;

import com.dnakolor.api.DTOs.LoginUserDTO;
import com.dnakolor.api.DTOs.RegisterUserDTO;
import com.dnakolor.api.models.User;
import com.dnakolor.api.response.LoginResponse;
import com.dnakolor.api.response.ResponseBody;
import com.dnakolor.api.services.AuthenticationService;
import com.dnakolor.api.services.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDto) {
        try{
            ResponseBody registeredUser = authenticationService.signup(registerUserDto);
            ResponseBody responseBody = new ResponseBody(registeredUser.getMessage(), registeredUser.getBody());
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            ResponseBody re = new ResponseBody("User registration failed", null);
            return ResponseEntity.badRequest().body(re);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        ResponseBody responseBody = new ResponseBody<>("User authenticated successfully", loginResponse);

        return ResponseEntity.ok(responseBody);
    }
}
