package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.*;
import edu.tust.neusoft.backend.model.dto.UpdateUserRequest;
import edu.tust.neusoft.backend.repository.AdminLoginLogRepository;
import edu.tust.neusoft.backend.repository.PortalLoginLogRepository;
import edu.tust.neusoft.backend.repository.UserRepository;
import edu.tust.neusoft.backend.repository.WalletRepository;
import edu.tust.neusoft.backend.repository.WalletLogRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PortalLoginLogRepository portalLoginLogRepository;
    private final AdminLoginLogRepository adminLoginLogRepository;
    private final WalletRepository walletRepository;
    private final WalletLogRepository walletLogRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PortalLoginLogRepository portalLoginLogRepository, AdminLoginLogRepository adminLoginLogRepository, WalletRepository walletRepository, WalletLogRepository walletLogRepository) {
        this.userRepository = userRepository;
        this.portalLoginLogRepository = portalLoginLogRepository;
        this.adminLoginLogRepository = adminLoginLogRepository;
        this.walletRepository = walletRepository;
        this.walletLogRepository = walletLogRepository;
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
    public Result loginByPhone(String phone, String password, String loginIp) {
        User user = userRepository.findByPhoneAndUserPassword(phone, password);
        if (user != null) {
            if (user.getUserStatus() != 1) {
                return Result.fail("用户被冻结");
            }

            // 记录登录日志
            PortalLoginLog loginLog = new PortalLoginLog();
            loginLog.setUserId(Math.toIntExact(user.getId()));
            loginLog.setLoginIp(loginIp);
            loginLog.setCreateTime(new Date());
            portalLoginLogRepository.save(loginLog);

            user.setUserPassword(null);  // 不返回密码
            return Result.success("登陆成功", user);
        }
        return Result.fail("登陆失败");
    }

    @Override
    public Result adminLoginByPhone(String phone, String password, String loginIp) {
        User user = userRepository.findByPhoneAndUserPassword(phone, password);
        if (user != null) {
            if (user.getUserStatus() != 1) {
                return Result.fail("用户被冻结");
            }

            // 记录管理员登录日志
            AdminLoginLog loginLog = new AdminLoginLog();
            loginLog.setUserId(Math.toIntExact(user.getId()));
            loginLog.setLoginIp(loginIp);
            loginLog.setCreateTime(new Date());
            adminLoginLogRepository.save(loginLog);

            user.setUserPassword(null);  // 不返回密码
            return Result.success("管理员登陆成功", user);
        }
        return Result.fail("管理员登陆失败");
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
    public Result getWalletBalance(int userId) {
        Wallet wallet = walletRepository.findByUserId((long) userId).get();
        if (wallet != null) {
            return Result.success("获取成功", wallet.getWalletBalance());
        }
        return Result.fail("用户钱包不存在");
    }

    @Override
    public Result chargeWallet(int userId, double amount) {
        Wallet wallet = walletRepository.findByUserId((long) userId).get();
        if (wallet != null) {
            wallet.setWalletBalance(wallet.getWalletBalance() + amount);
            wallet.setUpdateTime(new Date());
            walletRepository.save(wallet);
            return Result.success("充值成功", wallet.getWalletBalance());
        }
        return Result.fail("用户钱包不存在");
    }

    @Override
    public Result transferMoney(int userId, String targetPhone, double amount) {
        if (amount <= 0) {
            return Result.fail("转账金额必须大于零");
        }

        Wallet senderWallet = walletRepository.findByUserId((long) userId).get();
        if (senderWallet == null) {
            return Result.fail("发送方用户钱包不存在");
        }

        if (senderWallet.getWalletBalance() < amount) {
            return Result.fail("发送方用户钱包余额不足");
        }

        User targetUser = userRepository.findByPhone(targetPhone).orElse(null);
        if (targetUser == null) {
            return Result.fail("目标用户不存在");
        }

        Wallet receiverWallet = walletRepository.findByUserId(targetUser.getId()).get();
        if (receiverWallet == null) {
            return Result.fail("目标用户钱包不存在");
        }

        senderWallet.setWalletBalance(senderWallet.getWalletBalance() - amount);
        senderWallet.setUpdateTime(new Date());
        walletRepository.save(senderWallet);

        receiverWallet.setWalletBalance(receiverWallet.getWalletBalance() + amount);
        receiverWallet.setUpdateTime(new Date());
        walletRepository.save(receiverWallet);

        return Result.success("转账成功", null);
    }

    @Override
    public Result getWalletLogs(int userId) {
        Wallet wallet = walletRepository.findByUserId((long) userId).get();
        if (wallet == null) {
            return Result.fail("用户钱包不存在");
        }

        List<WalletLog> walletLogs = walletLogRepository.findByWalletId(wallet.getId());
        return Result.success("获取成功", walletLogs);
    }

    @Override
    public boolean existsById(int userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public Result getAllUsers() {
        List<User> users = userRepository.findAll();
        return Result.success("获取成功", users);
    }
}
