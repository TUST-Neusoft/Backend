package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.OrderDetail;
import edu.tust.neusoft.backend.repository.OrderDetailRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Result getOrderDetailByOrderNo(String orderNo) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderNo(orderNo);
        if (!orderDetails.isEmpty()) {
            return Result.success("获取成功", orderDetails);
        } else {
            return Result.fail("没有找到订单详情");
        }
    }
}
