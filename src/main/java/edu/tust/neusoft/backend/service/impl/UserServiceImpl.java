package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.repository.UserRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result register(User user) {
        Optional<User> existingUser = userRepository.findByPhone(user.getPhone());
        if (existingUser.isPresent()) {
            return Result.fail("手机号已存在！");
        }
        userRepository.save(user);
        return Result.success("注册成功", user);
    }

    @Override
    public Result loginByPhone(String phone, String password) {
        User user = userRepository.findByPhoneAndUserPassword(phone, password);
        if (user != null) {
            user.setUserPassword(null);  // 不返回密码
            return Result.success("登陆成功", user);
        }
        return Result.fail("登陆失败");
    }

    @Override
    public Result forgetPassword(String phone, String password, String userName) {
        User user = userRepository.findByPhoneAndUserName(phone, userName);
        if (user != null) {
            user.setUserPassword(password);
            userRepository.save(user);
            return Result.success("修改成功", user);
        }
        return Result.fail("修改失败");
    }

    @Override
    public Result getUserByPhoneAndPassword(String phone, String password) {
        User user = userRepository.findByPhoneAndUserPassword(phone, password);
        if (user != null) {
            user.setUserPassword(null);  // 不返回密码
            return Result.success("获取成功", user);
        }
        return Result.fail("获取失败");
    }

    @Override
    public Result getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserPassword(null);  // 不返回密码
            return Result.success("获取成功", user);
        }
        return Result.fail("用户不存在");
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        if (updateUserRequest == null) {
            return Result.fail("更新请求不能为空");
        }

        Optional<User> userOptional = userRepository.findById(updateUserRequest.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPhone(updateUserRequest.getPhone());
            user.setUserName(updateUserRequest.getUserName());
            user.setSex(updateUserRequest.getSex());
            user.setMail(updateUserRequest.getMail());
            user.setAvatar(updateUserRequest.getAvatar());
            user.setUserStatus(updateUserRequest.getUserStatus());

            // 设置lastLoginTime
            if (updateUserRequest.getLastLoginTime() != null) {
                user.setLastLoginTime(updateUserRequest.getLastLoginTime());
            }

            // 设置updateTime为当前时间
            user.setUpdateTime(new Date());

            userRepository.save(user);
            user.setUserPassword(null);  // 不返回密码
            return Result.success("更新成功", user);
        }
        return Result.fail("用户不存在");
    }

    @Override
    public Result updatePassword(int userId, String userPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserPassword(userPassword);

            // 设置updateTime为当前时间
            user.setUpdateTime(new Date());

            userRepository.save(user);
            return Result.success("密码更新成功", user);
        }
        return Result.fail("用户不存在");
    }

    @Override
    public boolean existsById(int userId) {
        return userRepository.existsById(userId);
    }
}
