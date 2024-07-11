package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.OrderRequest;
import edu.tust.neusoft.backend.model.dto.PayOrderRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.OrderDetailService;
import edu.tust.neusoft.backend.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersController(OrdersService ordersService, OrderDetailService orderDetailService) {
        this.ordersService = ordersService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/getMyOrders")
    public Result getMyOrders(@CookieValue("user_id") int userId) {
        return ordersService.getOrdersByUserId(userId);
    }

    @GetMapping("/getOrders")
    public Result getOrders(@RequestParam("order_no") String orderNo) {
        return orderDetailService.getOrderDetailByOrderNo(orderNo);
    }

    @PostMapping("/addOrders")
    public Result addOrders(@RequestBody OrderRequest OrderRequest, @CookieValue("user_id") int userId) {
        return ordersService.addOrders(userId, OrderRequest);
    }

    @PostMapping("/payOrders")
    public Result payOrders(@RequestBody PayOrderRequest payOrderRequest, @CookieValue("user_id") int userId) {
        return ordersService.payOrders(userId, payOrderRequest);
    }
}
