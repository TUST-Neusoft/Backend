package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.repository.UserRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result register(User user) {
        // 检查手机号是否已经存在
        Optional<User> existingUser = userRepository.findByPhone(user.getPhone());
        if (existingUser.isPresent()) {
            return Result.fail("手机号已存在！");
        }
        // 如果没有手机号，则保存新用户
        userRepository.save(user);
        return Result.success("注册成功", user);
    }

    @Override
    public Result loginByPhone(String phone, String password) {
        User user = userRepository.findByPhoneAndUserPassword(phone, password);
        if (user != null) {
            // 可以选择不返回密码等敏感信息
            user.setUserPassword(null);
            return Result.success("登陆成功", user);
        }
        return Result.fail("登陆失败");
    }

    @Override
    public Result forgetPassword(String phone, String password, String userName) {
        User user = userRepository.findByPhoneAndUserName(phone, userName);
        if (user != null) {
            // 可以选择不返回密码等敏感信息
            user.setUserPassword(password);
            userRepository.save(user);
            return Result.success("修改成功", user);
        }
        return Result.fail("修改失败");
    }
}