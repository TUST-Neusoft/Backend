package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.ForgetRequest;
import edu.tust.neusoft.backend.model.dto.LoginRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return userService.loginByPhone(loginRequest.getPhone(), loginRequest.getPassword(), response);
    }

    @PostMapping("/forget")
    public Result forget(@RequestBody ForgetRequest forgetRequest) {
        return userService.forgetPassword(forgetRequest.getPhone(), forgetRequest.getPassword(), forgetRequest.getUserName());
    }
}
