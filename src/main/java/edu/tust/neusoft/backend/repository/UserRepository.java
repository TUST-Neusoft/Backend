package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 根据手机号查找用户
    Optional<User> findByPhone(String phone);

    User findById(Long id);

    // 根据手机号和密码查找用户
    User findByPhoneAndUserPassword(String phone, String password);

    // 根据手机号和用户名查找用户
    User findByPhoneAndUserName(String phone, String userName);

    // 检查 user_id 是否存在
    boolean existsById(Long id);
}
