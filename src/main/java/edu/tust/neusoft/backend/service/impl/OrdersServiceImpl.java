package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Orders;
import edu.tust.neusoft.backend.model.OrderDetail;
import edu.tust.neusoft.backend.repository.OrdersRepository;
import edu.tust.neusoft.backend.repository.OrderDetailRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Result getOrdersByUserId(int userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            return Result.fail("没有找到订单");
        }
        return Result.success("获取成功", orders);
    }
}
