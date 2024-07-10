package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getAll")
    public Result getAllCharges(@CookieValue("user_id") Long userId) {
        return paymentService.getAllChargesByUserId(userId);
    }

    @GetMapping("/getDetail")
    public Result getChargeDetails(@RequestParam("charge_no") String chargeNo) {
        return paymentService.getChargeDetailsByChargeNo(chargeNo);
    }
}
