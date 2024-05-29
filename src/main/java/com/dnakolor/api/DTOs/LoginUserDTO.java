package com.dnakolor.api.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDTO {
    private String email;

    private String password;
}
