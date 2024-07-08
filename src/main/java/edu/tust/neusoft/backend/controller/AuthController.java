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
        Result result = userService.loginByPhone(loginRequest.getPhone(), loginRequest.getPassword());
        if (result.getCode() == 200) {
            User user = (User) result.getData();
            Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return result;
    }

    @PostMapping("/forget")
    public Result forget(@RequestBody ForgetRequest forgetRequest) {
        return userService.forgetPassword(forgetRequest.getPhone(), forgetRequest.getPassword(), forgetRequest.getUserName());
    }
}
