package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.OrderAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderAdmin")
public class OrderAdminController {
    private final OrderAdminService orderAdminService;

    @Autowired
    public OrderAdminController(OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    @GetMapping("/getAllorder")
    public Result getAllOrders(@CookieValue("user_id") int userId) {
        return orderAdminService.getAllOrdersByUserId(userId);
    }

    @GetMapping("/getDetail")
    public Result getOrderDetail(@RequestParam("order_no") String orderNo) {
        return orderAdminService.getOrderDetailByOrderNo(orderNo);
    }

    @PostMapping("/changeStatus")
    public Result changeOrderStatus(@RequestParam("order_no") String orderNo) {
        return orderAdminService.changeOrderStatus(orderNo);
    }
}
