package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.ResetGoodsRequest;
import edu.tust.neusoft.backend.response.Result;

public interface GoodsService {
    Result getGoodsByCategoryId(int categoryId);
    Result getAllGoods();
    Result getGoodsByGoodsNo(String goodsNo);
    Result searchGoodsByGoodsName(String goodsName);
    boolean existsByGoodsNo(String goodsNo);
    Result resetGoodsMsg(ResetGoodsRequest resetGoodsRequest);
    Result getGoodsStoreByGoodsNo(String goodsNo); // 新增的方法
}

