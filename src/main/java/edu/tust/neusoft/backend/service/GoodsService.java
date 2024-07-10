package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface GoodsService {
    Result getGoodsByCategoryId(int categoryId);
    Result getGoodsByGoodsNo(String goodsNo);
    Result searchGoodsByGoodsName(String goodsName);
    boolean existsByGoodsNo(String goodsNo);
    Result getAllGoods(); // 添加方法签名
}
