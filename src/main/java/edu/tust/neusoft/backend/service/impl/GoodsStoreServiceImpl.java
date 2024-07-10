package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.GoodsStore;
import edu.tust.neusoft.backend.model.dto.ResetGoodsMsgRequest;
import edu.tust.neusoft.backend.repository.GoodsStoreRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsStoreServiceImpl implements GoodsStoreService {

    private final GoodsStoreRepository goodsStoreRepository;

    @Autowired
    public GoodsStoreServiceImpl(GoodsStoreRepository goodsStoreRepository) {
        this.goodsStoreRepository = goodsStoreRepository;
    }

    @Override
    public Result getGoodsByStoreNo(String storeNo) {
        List<GoodsStore> goodsStoreList = goodsStoreRepository.findByStoreNo(storeNo);
        if (goodsStoreList.isEmpty()) {
            return Result.fail("该门店没有对应的商品");
        }

        return Result.success("获取成功", goodsStoreList);
    }

    @Override
    public Result resetGoodsMsg(ResetGoodsMsgRequest resetGoodsMsgRequest) {
        GoodsStore goodsStore = goodsStoreRepository.findByStoreNoAndGoodsNo(resetGoodsMsgRequest.getStoreNo(), resetGoodsMsgRequest.getGoodsNo());
        if (goodsStore == null) {
            return Result.fail("未找到指定的商品");
        }

        goodsStore.setGoodsStock(resetGoodsMsgRequest.getGoodsStock());
        goodsStore.setGoodsPrice(resetGoodsMsgRequest.getGoodsPrice());
        goodsStore.setUpdateTime(LocalDateTime.now());
        goodsStoreRepository.save(goodsStore);

        return Result.success("商品信息更新成功", goodsStore);
    }
}
