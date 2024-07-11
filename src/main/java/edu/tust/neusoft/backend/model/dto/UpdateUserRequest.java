package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserRequest {
    private Long id;

    private String phone;

    @JsonProperty("user_name")
    private String userName;

    private String sex;

    private String mail;

    private String avatar;

    @JsonProperty("last_login_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    @JsonProperty("user_status")
    private int userStatus;
}
