package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.TransferMoneyRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final UserService userService;

    @Autowired
    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getMyWallet")
    public Result getWalletBalance(@CookieValue int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return userService.getWalletBalance(userId);
    }

    @PostMapping("/chargeMoney")
    public Result chargeWallet(@CookieValue int userId, @RequestParam double amount) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        if (amount <= 0) {
            return Result.fail("充值金额必须大于零");
        }
        return userService.chargeWallet(userId, amount);
    }

    @PostMapping("/transferMoney")
    public Result transferMoney(@CookieValue int userId, @RequestBody TransferMoneyRequest transferMoneyRequest) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return userService.transferMoney(userId, transferMoneyRequest.getPhone(), transferMoneyRequest.getMoney());
    }
}
