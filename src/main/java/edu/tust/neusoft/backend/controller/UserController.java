package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public Result getCurrentUser(@CookieValue int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return userService.getUserById(userId);
    }

    @PostMapping("/updateUser")
    public Result updateUser(@CookieValue int userId, @RequestBody UpdateUserRequest updateUserRequest) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        if (updateUserRequest == null) {
            return Result.fail("更新请求不能为空");
        }
        if (userId != updateUserRequest.getId()) {
            return Result.fail("无法更新其他用户的信息");
        }
        return userService.updateUser(updateUserRequest);
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@CookieValue int userId, @RequestParam("user_password") String userPassword) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        if (userPassword == null || userPassword.isEmpty()) {
            return Result.fail("密码不能为空");
        }
        return userService.updatePassword(userId, userPassword);
    }
}
