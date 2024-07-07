package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.response.Result;

public interface UserService {
    Result register(User user); // 注册方法
    Result loginByPhone(String phone, String password);  // 登录方法
    Result forgetPassword(String phone, String password, String userName);
    boolean existsById(int userId);
}
