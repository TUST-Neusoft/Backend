package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Carts;
import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.GoodsStore;
import edu.tust.neusoft.backend.model.Store;
import edu.tust.neusoft.backend.model.dto.CartDetailDTO;
import edu.tust.neusoft.backend.model.dto.GoodsDTO;
import edu.tust.neusoft.backend.model.dto.StoreDTO;
import edu.tust.neusoft.backend.repository.CartsRepository;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.repository.GoodsStoreRepository;
import edu.tust.neusoft.backend.repository.StoreRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.CartsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartsServiceImpl implements CartsService {
    private final CartsRepository cartsRepository;
    private final GoodsRepository goodsRepository;
    private final StoreRepository storeRepository;
    private final GoodsStoreRepository goodsStoreRepository;

    public CartsServiceImpl(CartsRepository cartsRepository, GoodsRepository goodsRepository, StoreRepository storeRepository, GoodsStoreRepository goodsStoreRepository) {
        this.cartsRepository = cartsRepository;
        this.goodsRepository = goodsRepository;
        this.storeRepository = storeRepository;
        this.goodsStoreRepository = goodsStoreRepository;
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

            Optional<GoodsStore> goodsStoreOpt = goodsStoreRepository.findByGoodsNoAndStoreNo(goodsNo, storeNo);
            Double price = null;
            Integer stock = null;

            if (goodsStoreOpt.isPresent()) {
                GoodsStore goodsStore = goodsStoreOpt.get();
                price = goodsStore.getPrice();
                stock = goodsStore.getStock();
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
}
