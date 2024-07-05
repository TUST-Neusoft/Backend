package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.LoginRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zhangwang")
public class ZhangWangController {

    private final UserService userService;

    @Autowired
    public ZhangWangController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result loginUser(@RequestBody LoginRequest request) {
        return userService.loginByPhone(request.getPhone(), request.getPassword());
    }
}
