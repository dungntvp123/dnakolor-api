package com.dnakolor.api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String email;
    private String password;
    private String fullName;
}
