package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetRequest {
    @JsonProperty("user_name")
    private String userName;
    private String phone;
    private String password;
}
