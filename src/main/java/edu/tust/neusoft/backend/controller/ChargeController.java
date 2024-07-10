package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charge")
public class ChargeController {
    private final ChargeService chargeService;

    @Autowired
    public ChargeController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @GetMapping("/getMyCharge")
    public Result getMyCharge(@CookieValue(value = "user_id", defaultValue = "-1") int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return chargeService.getMyCharge(userId);
    }
}
