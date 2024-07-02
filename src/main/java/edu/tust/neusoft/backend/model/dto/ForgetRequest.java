package edu.tust.neusoft.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetRequest {
    private String userName;
    private String phone;
    private String password;
}
