package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.*;
import edu.tust.neusoft.backend.model.dto.CartDetailDTO;
import edu.tust.neusoft.backend.model.dto.GoodsDTO;
import edu.tust.neusoft.backend.model.dto.StoreDTO;
import edu.tust.neusoft.backend.repository.CartsRepository;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.repository.GoodsStoreRepository;
import edu.tust.neusoft.backend.repository.StoreRepository;
import edu.tust.neusoft.backend.repository.UserRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.CartsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartsServiceImpl implements CartsService {
    private final CartsRepository cartsRepository;
    private final GoodsRepository goodsRepository;
    private final StoreRepository storeRepository;
    private final GoodsStoreRepository goodsStoreRepository;
    private final UserRepository userRepository;

    public CartsServiceImpl(CartsRepository cartsRepository, GoodsRepository goodsRepository, StoreRepository storeRepository, GoodsStoreRepository goodsStoreRepository, UserRepository userRepository) {
        this.cartsRepository = cartsRepository;
        this.goodsRepository = goodsRepository;
        this.storeRepository = storeRepository;
        this.goodsStoreRepository = goodsStoreRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Result getCarts(int userId) {
        List<Carts> carts = cartsRepository.findByUserId(userId);
        List<CartDetailDTO> cartList = new ArrayList<>();

        for (Carts cart : carts) {
            String goodsNo = cart.getGoodsNo();
            Goods goods = goodsRepository.findByGoodsNo(goodsNo);
            String storeNo = cart.getStoreNo();
            Store store = storeRepository.findByStoreNo(storeNo);

            Optional<GoodsStore> goodsStoreOpt = Optional.ofNullable(goodsStoreRepository.findByGoodsNoAndStoreNo(goodsNo, storeNo));
            Double price = null;
            Integer stock = null;

            if (goodsStoreOpt.isPresent()) {
                GoodsStore goodsStore = goodsStoreOpt.get();
                price = goodsStore.getGoodsPrice();
                stock = goodsStore.getGoodsStock();
            }

            // Assemble DTOs
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setId(goods.getId());
            goodsDTO.setGoodsNo(goods.getGoodsNo());
            goodsDTO.setGoodsName(goods.getGoodsName());
            goodsDTO.setCategoryId(goods.getCategoryId());
            goodsDTO.setGoodsIntroduce(goods.getGoodsIntroduce());
            goodsDTO.setGoodsContent(goods.getGoodsContent());
            goodsDTO.setGoodState(goods.getGoodState());
            goodsDTO.setGoodsPicture(goods.getGoodsPicture());
            goodsDTO.setGoodsPrice(goods.getGoodsPrice());
            goodsDTO.setCreateTime(goods.getCreateTime());
            goodsDTO.setUpdateTime(goods.getUpdateTime());

            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setId(store.getId());
            storeDTO.setAreaId(store.getAreaId());
            storeDTO.setStoreNo(store.getStoreNo());
            storeDTO.setStoreName(store.getStoreName());
            storeDTO.setStoreAddress(store.getStoreAddress());
            storeDTO.setMaxLongitude(store.getMaxLongitude());
            storeDTO.setMaxLatitude(store.getMaxLatitude());
            storeDTO.setMinLongitude(store.getMinLongitude());
            storeDTO.setMinLatitude(store.getMinLatitude());
            storeDTO.setStoreIntroduce(store.getStoreIntroduce());
            storeDTO.setStartTime(store.getStartTime());
            storeDTO.setCloseTime(store.getCloseTime());
            storeDTO.setStoreStatus(store.getStoreStatus());
            storeDTO.setCreateTime(store.getCreateTime());
            storeDTO.setUpdateTime(store.getUpdateTime());
            storeDTO.setLongitude(store.getLongitude());
            storeDTO.setLatitude(store.getLatitude());

            CartDetailDTO cartDTO = new CartDetailDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setUserId(cart.getUserId());
            cartDTO.setStoreNo(storeDTO);
            cartDTO.setGoodsNo(goodsDTO);
            cartDTO.setAmount(cart.getAmount());
            cartDTO.setCreateTime(cart.getCreateTime());
            cartDTO.setUpdateTime(cart.getUpdateTime());
            cartDTO.setPrice(price);
            cartDTO.setStock(stock);

            cartList.add(cartDTO);
        }

        return Result.success("获取成功", cartList);
    }

    @Override
    public Result addCart(Carts cart) {
        // 检查 cart.getUserId() 是否为 null
        if (cart.getUserId() == null) {
            return Result.fail("User ID is null");
        }

        // 检查用户是否存在
        Optional<User> userOpt = userRepository.findById(cart.getUserId());
        if (!userOpt.isPresent()) {
            return Result.fail("User does not exist");
        }

        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());
        cartsRepository.save(cart);
        return Result.success("添加成功", cart);
    }

    @Override
    public Result updateCart(Carts cart) {
        System.out.println("Received cart data: " + cart);
        // 检查传入的购物车数据是否为 null
        if (cart == null) {
            return Result.fail("传入的购物车数据为 null");
        }

        // 检查用户 ID 是否为 null
        if (cart.getUserId() == null) {
            return Result.fail("用户 ID 为 null");
        }

        // 检查用户是否存在
        Optional<User> userOpt = userRepository.findById(cart.getUserId());
        if (!userOpt.isPresent()) {
            return Result.fail("用户不存在");
        }

        // 根据 userId 查询现有的购物车项
        List<Carts> existingCarts = cartsRepository.findByUserId(Math.toIntExact(cart.getUserId()));
        if (existingCarts.isEmpty()) {
            return Result.fail("根据用户 ID 未找到购物车");
        }

        // 更新所有找到的购物车条目
        for (Carts existingCart : existingCarts) {
            existingCart.setGoodsNo(cart.getGoodsNo());
            existingCart.setStoreNo(cart.getStoreNo());
            existingCart.setAmount(cart.getAmount());
            existingCart.setUpdateTime(new Date());
            cartsRepository.save(existingCart);
        }

        return Result.success("购物车更新成功", existingCarts);
    }

    public Result deleteCart(Long cartId) {
        Optional<Carts> cart = cartsRepository.findById(Math.toIntExact(cartId));
        if (!cart.isPresent()) {
            return Result.fail("购物车不存在");
        }

        cartsRepository.deleteById(Math.toIntExact(cartId));
        return Result.success("购物车删除成功",deleteCart(cartId));
    }

}
