package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.Carts;
import edu.tust.neusoft.backend.response.Result;

public interface CartsService {
    Result getCarts(Long userId);
    Result addCart(Carts cart);
    Result updateCart(Carts cart);
    Result deleteCart(Integer cartId);
}
