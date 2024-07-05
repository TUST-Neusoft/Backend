package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.User;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.CartsService;
import edu.tust.neusoft.backend.service.UserService;
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
        return cartsService.getCarts(userId);
    }

}
