package edu.tust.neusoft.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    // Getters and Setters
    private String phone;
    private String password;

}
