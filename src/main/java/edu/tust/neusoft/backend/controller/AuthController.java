package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.ForgetRequest;
import edu.tust.neusoft.backend.model.dto.LoginRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import edu.tust.neusoft.backend.utils.MD5Utils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static edu.tust.neusoft.backend.config.BaseConfig.SUCCESS;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    @Resource
    private StringRedisTemplate redis;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        String loginIp = request.getRemoteAddr();
        Result result = userService.loginByPhone(loginRequest.getPhone(), loginRequest.getPassword(), loginIp);
        if (result.getCode() == SUCCESS) {
            User user = (User) result.getData();
            MD5Utils md5Utils = new MD5Utils();
            String token = md5Utils.generateMd5Token();
            redis.opsForValue().set(String.valueOf(user.getId()), token, 1, TimeUnit.DAYS);

            // 创建并配置 userId Cookie
            Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(1)); // 设置 Cookie 有效期为 1 天
            userIdCookie.setPath("/"); // 设置 Cookie 的路径

            // 创建并配置 token Cookie
            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setHttpOnly(true);
            tokenCookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(1)); // 设置 Cookie 有效期为 1 天
            tokenCookie.setPath("/"); // 设置 Cookie 的路径

            // 将 Cookies 添加到响应中
            response.addCookie(userIdCookie);
            response.addCookie(tokenCookie);
        }
        return result;
    }

    @PostMapping("/forget")
    public Result forget(@RequestBody ForgetRequest forgetRequest) {
        return userService.forgetPassword(forgetRequest.getPhone(), forgetRequest.getPassword(), forgetRequest.getUserName());
    }
}
