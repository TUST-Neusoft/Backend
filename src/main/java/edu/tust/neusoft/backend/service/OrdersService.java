package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.OrderRequest;
import edu.tust.neusoft.backend.model.dto.PayOrderRequest;
import edu.tust.neusoft.backend.response.Result;

public interface OrdersService {
    Result getOrdersByUserId(int userId);
    Result addOrders(int userId, OrderRequest orderRequest);
    Result payOrders(int userId, PayOrderRequest payOrderRequest);
}
