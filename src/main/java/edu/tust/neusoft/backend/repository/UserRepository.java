package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查找用户
    Optional<User> findByUserName(String userName);

    // 根据电子邮件查找用户（如果需要）
    User findByPhoneAndPassword(String phone, String password);  // 新增方法

}
