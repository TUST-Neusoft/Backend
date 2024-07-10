package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface OrderAdminService {
    Result getAllOrdersByUserId(int userId);
    Result getOrderDetailByOrderNo(String orderNo);
    Result changeOrderStatus(String orderNo);
}
