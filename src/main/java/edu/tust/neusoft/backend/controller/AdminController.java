package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.LoginRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        String loginIp = request.getRemoteAddr();
        Result result = userService.adminLoginByPhone(loginRequest.getPhone(), loginRequest.getPassword(), loginIp);
        if (result.getCode() == 200) {
            User user = (User) result.getData();
            Cookie cookie = new Cookie("admin_id", String.valueOf(user.getId()));
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return result;
    }

    @GetMapping("/GetAllUser")
    public Result getAllUser() {
        return userService.getAllUsers();
    }
}
