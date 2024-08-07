package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.*;
import edu.tust.neusoft.backend.model.dto.GoodsRequest;
import edu.tust.neusoft.backend.model.dto.OrderRequest;
import edu.tust.neusoft.backend.model.dto.PayOrderRequest;
import edu.tust.neusoft.backend.repository.*;
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

    private final OrdersRepository ordersRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final CartsRepository cartsRepository;

    private final GoodsStoreRepository goodsStoreRepository;

    private final WalletRepository walletRepository;

    private final WalletLogRepository walletLogRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository, OrderDetailRepository orderDetailRepository, CartsRepository cartsRepository, GoodsStoreRepository goodsStoreRepository, WalletRepository walletRepository, WalletLogRepository walletLogRepository) {
        this.ordersRepository = ordersRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartsRepository = cartsRepository;
        this.goodsStoreRepository = goodsStoreRepository;
        this.walletRepository = walletRepository;
        this.walletLogRepository = walletLogRepository;
    }

    @Override
    public Result getOrdersByUserId(int userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        return Result.success("获取成功", orders);
    }

    @Override
    @Transactional
    public Result addOrders(int userId, OrderRequest orderRequest) {
        double totalOrderPrice = 0.0;
        Optional<Orders> existingOrderOptional = ordersRepository.findByOrderNo(orderRequest.getOrderNo());

        for (GoodsRequest goodsRequest : orderRequest.getGoods()) {
            Optional<Carts> optionalCart = cartsRepository.findByUserIdAndGoodsNoAndStoreNo((long)userId, goodsRequest.getGoodsNo(), goodsRequest.getStoreNo());
            Optional<GoodsStore> optionalGoodsStore = goodsStoreRepository.findByGoodsNoAndStoreNo(goodsRequest.getGoodsNo(), goodsRequest.getStoreNo());

            if (optionalCart.isPresent() && optionalGoodsStore.isPresent()) {
                Carts cart = optionalCart.get();
                GoodsStore goodsStore = optionalGoodsStore.get();

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderNo(orderRequest.getOrderNo());
                orderDetail.setStoreNo(goodsRequest.getStoreNo());
                orderDetail.setGoodsNo(goodsRequest.getGoodsNo());
                orderDetail.setGoodsAmount(cart.getAmount());
                orderDetail.setGoodsPrice(goodsStore.getGoodsPrice());
                orderDetail.setTotalPrice(cart.getAmount() * goodsStore.getGoodsPrice());
                orderDetail.setCreateTime(LocalDateTime.now());
                orderDetail.setUpdateTime(LocalDateTime.now());

                orderDetailRepository.save(orderDetail);

                totalOrderPrice += orderDetail.getTotalPrice();

                // 删除购物车表中对应数据
                cartsRepository.deleteByUserIdAndGoodsNoAndStoreNo((long)userId, goodsRequest.getGoodsNo(), goodsRequest.getStoreNo());
            } else {
                return Result.fail("未找到对应的购物车信息或商品信息");
            }
        }

        if (existingOrderOptional.isPresent()) {
            // 更新订单主表数据的 total_price
            Orders existingOrder = existingOrderOptional.get();
            existingOrder.setTotalPrice(existingOrder.getTotalPrice() + totalOrderPrice);
            existingOrder.setUpdateTime(LocalDateTime.now());

            ordersRepository.save(existingOrder);
        } else {
            // 新增订单主表数据
            Orders order = new Orders();
            order.setOrderNo(orderRequest.getOrderNo());
            order.setUserId(userId);
            order.setTotalPrice(totalOrderPrice);
            order.setPaymentType("1");
            order.setPaymentSubtype("1");
            order.setDeliveryType("2");
            order.setOrderState("-1");
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());

            ordersRepository.save(order);
        }

        return Result.success("订单添加成功", null);
    }


    @Override
    @Transactional
    public Result payOrders(int userId, PayOrderRequest payOrderRequest) {
        // 查询钱包信息
        Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);
        if (optionalWallet.isPresent()) {
            return Result.fail("未找到用户钱包信息");
        }

        Wallet wallet = optionalWallet.get();

        // 验证钱包密码
        if (!wallet.getWalletPassword().equals(payOrderRequest.getWalletPassword())) {
            return Result.fail("钱包密码错误");
        }

        // 查询订单信息
        Optional<Orders> optionalOrder = ordersRepository.findById(Integer.parseInt(payOrderRequest.getOrdersId()));
        if (optionalOrder.isPresent()) {
            return Result.fail("未找到对应的订单");
        }

        Orders order = optionalOrder.get();

        // 验证订单所属用户
        if (order.getUserId() != userId) {
            return Result.fail("订单不属于当前用户");
        }

        // 比较余额与订单总价
        if (order.getTotalPrice() > wallet.getWalletBalance()) {
            return Result.fail("余额不足");
        }

        // 扣除余额
        wallet.setWalletBalance(wallet.getWalletBalance() - order.getTotalPrice());
        walletRepository.save(wallet);

        // 更新商品库存
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderNo(order.getOrderNo());
        for (OrderDetail orderDetail : orderDetails) {
            Optional<GoodsStore> optionalGoodsStore = goodsStoreRepository.findByGoodsNoAndStoreNo(orderDetail.getGoodsNo(), orderDetail.getStoreNo());
            if (optionalGoodsStore.isPresent()) {
                GoodsStore goodsStore = optionalGoodsStore.get();
                goodsStore.setGoodsStock(goodsStore.getGoodsStock() - orderDetail.getGoodsAmount());
                goodsStoreRepository.save(goodsStore);
            } else {
                return Result.fail("未找到对应的商品库存信息");
            }
        }

        // 更新订单状态为已支付
        order.setOrderState("1");
        ordersRepository.save(order);

        // 记录钱包使用日志
        for (OrderDetail orderDetail : orderDetails) {
            WalletLog walletLog = new WalletLog();
            walletLog.setWalletId(wallet.getId());
            walletLog.setOrderNo(orderDetail.getOrderNo());
            walletLog.setAmount(orderDetail.getGoodsAmount());
            walletLog.setType("1");
            walletLog.setState("1");
            walletLog.setCreateTime(LocalDateTime.now());
            walletLogRepository.save(walletLog);
        }

        return Result.success("支付成功", null);
    }
}
