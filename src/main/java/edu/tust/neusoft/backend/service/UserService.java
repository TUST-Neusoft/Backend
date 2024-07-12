package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.response.Result;

public interface UserService {
    Result register(User user); // 注册方法
    Result loginByPhone(String phone, String password, String loginIp);

    Result adminLoginByPhone(String phone, String password, String loginIp);

    Result forgetPassword(String phone, String password, String userName);
    Result getUserByPhoneAndPassword(String phone, String password); // 获取用户信息的方法
    Result getUserById(Long userId); // 根据ID获取用户信息的方法
    Result updateUser(UpdateUserRequest updateUserRequest); // 更新用户信息的方法
    Result updatePassword(Long userId, String userPassword); // 更新用户密码的方法

    Result getWalletBalance(int userId);

    Result chargeWallet(int userId, double amount);

    Result transferMoney(int userId, String targetPhone, double amount);

    Result getWalletLogs(int userId);

    boolean existsById(Long userId);

    Result getAllUsers();
}
