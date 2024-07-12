package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.Carts;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.CartsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartsController {
    private final CartsService cartsService;

    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }

    @GetMapping("/getMyCarts")
    public Result getMyCarts(@CookieValue int userId) {
        return cartsService.getCarts((long)userId);
    }

    @PostMapping("/addCarts")
    public Result addCarts(@CookieValue int userId, @RequestBody Carts cart) {
        cart.setUserId((long) userId);
        return cartsService.addCart(cart);
    }

    @PostMapping("/updateCarts")
    public  Result updateCart(@RequestBody Carts cart) {
        return cartsService.updateCart(cart);
    }

    @GetMapping("/deleteCarts")
    public Result deleteCart(@CookieValue Integer cartId) {
        System.out.println("Received carts_id: " + cartId);// 注意参数名匹配请求中的 'carts_id'
        return cartsService.deleteCart(cartId);
    }

}
