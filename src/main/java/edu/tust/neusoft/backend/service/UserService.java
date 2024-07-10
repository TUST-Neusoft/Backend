package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.response.Result;

import java.util.List;

public interface UserService {
    Result register(User user); // 注册方法
    Result loginByPhone(String phone, String password, String loginIp);  // 登录方法，包含登录IP
    Result adminLoginByPhone(String phone, String password, String loginIp);  // 管理员登录方法，包含登录IP
    Result forgetPassword(String phone, String password, String userName);
    Result getUserByPhoneAndPassword(String phone, String password); // 获取用户信息的方法
    Result getUserById(int userId); // 根据ID获取用户信息的方法
    Result updateUser(UpdateUserRequest updateUserRequest); // 更新用户信息的方法
    Result updatePassword(int userId, String userPassword); // 更新用户密码的方法
    Result getWalletBalance(int userId); // 获取钱包余额的方法
    Result chargeWallet(int userId, double amount); // 充值钱包的方法
    Result transferMoney(int userId, String targetPhone, double amount); // 转账的方法
    Result getWalletLogs(int userId); // 获取钱包日志的方法
    boolean existsById(int userId);
    Result getAllUsers(); // 获取所有用户的方法
}
