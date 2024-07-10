package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.OrderDetail;
import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.Orders;
import edu.tust.neusoft.backend.model.dto.OrderDetailResponse;
import edu.tust.neusoft.backend.repository.OrderDetailRepository;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.repository.OrdersRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.OrderAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderAdminServiceImpl implements OrderAdminService {
    private final OrderDetailRepository orderDetailRepository;
    private final GoodsRepository goodsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderAdminServiceImpl(OrderDetailRepository orderDetailRepository, GoodsRepository goodsRepository, OrdersRepository ordersRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.goodsRepository = goodsRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Result getAllOrdersByUserId(int userId) {
        // Implementation for getAllOrdersByUserId if required
        return null;
    }

    @Override
    public Result getOrderDetailByOrderNo(String orderNo) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderNo(orderNo);
        if (orderDetails.isEmpty()) {
            return Result.fail("没有找到订单详情");
        }

        // 用查询到的所有数据替换输出结果中的 goodsNo 一项
        List<OrderDetailResponse> updatedOrderDetails = orderDetails.stream().map(orderDetail -> {
            Goods goods = goodsRepository.findByGoodsNo(orderDetail.getGoodsNo());
            OrderDetailResponse response = new OrderDetailResponse();
            response.setId(orderDetail.getId());
            response.setOrderNo(orderDetail.getOrderNo());
            response.setStoreNo(orderDetail.getStoreNo());
            response.setGoodsAmount(orderDetail.getGoodsAmount());
            response.setGoodsPrice(orderDetail.getGoodsPrice());
            response.setTotalPrice(orderDetail.getTotalPrice());
            response.setCreateTime(orderDetail.getCreateTime());
            response.setUpdateTime(orderDetail.getUpdateTime());

            if (goods != null) {
                response.setGoodsName(goods.getGoodsName());
                response.setCategoryId(goods.getCategoryId());
                response.setGoodsIntroduce(goods.getGoodsIntroduce());
                response.setGoodsContent(goods.getGoodsContent());
                response.setGoodsState(goods.getGoodsState());
                response.setGoodsPicture(goods.getGoodsPicture());
                response.setGoodsMarketPrice(goods.getGoodsPrice());
                response.setGoodsCreateTime(goods.getCreateTime());
                response.setGoodsUpdateTime(goods.getUpdateTime());
            }

            return response;
        }).collect(Collectors.toList());

        return Result.success("获取成功", updatedOrderDetails);
    }

    @Override
    public Result changeOrderStatus(String orderNo) {
        Optional<Orders> optionalOrder = ordersRepository.findByOrderNo(orderNo);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setOrderState(String.valueOf(3)); // 修改订单状态为3
            ordersRepository.save(order);
            return Result.success("已发货", null);
        } else {
            return Result.fail("订单未找到");
        }
    }
}
