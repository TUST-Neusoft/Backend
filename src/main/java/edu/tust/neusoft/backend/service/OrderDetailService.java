package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface OrderDetailService {
    Result getOrderDetailByOrderNo(String orderNo);
}
