package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.User;

public interface UserService {
    User register(User user); // 注册方法
    User loginByPhone(String phone, String password);  // 登录方法

}
