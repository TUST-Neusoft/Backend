package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class WalletLogController {
    private final UserService userService;

    @Autowired
    public WalletLogController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getWalletLogs")
    public Result getWalletLogs(@CookieValue(value = "user_id", defaultValue = "-1") int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return userService.getWalletLogs(userId);
    }
}
