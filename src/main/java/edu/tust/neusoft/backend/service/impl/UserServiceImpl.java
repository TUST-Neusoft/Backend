package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.repository.UserRepository;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        // 检查用户名是否已经存在
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // 如果没有现有用户，则保存新用户
        return userRepository.save(user);
    }

    @Override
    public User loginByPhone(String phone, String password) {
        return userRepository.findByPhoneAndPassword(phone, password);
    }
}