package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.response.Result;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    Result register(User user); // 注册方法

    Result loginByPhone(String phone, String password, HttpServletResponse response);  // 登录方法

    Result forgetPassword(String phone, String password, String userName);

    Result getUserByPhoneAndPassword(String phone, String password); // 获取用户信息的方法

    Result getUserById(int userId); // 根据ID获取用户信息的方法

    Result updateUser(UpdateUserRequest updateUserRequest); // 更新用户信息的方法

    Result updatePassword(int userId, String userPassword); // 更新用户密码的方法

    boolean existsById(int userId);
}
