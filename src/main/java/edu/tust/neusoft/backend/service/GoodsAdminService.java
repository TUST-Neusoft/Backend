package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface GoodsAdminService {
    Result getAllSpecialGoods();
    Result deleteGoodsByGoodsNo(String goodsNo);
    Result addGoodsByGoodsNo(String goodsNo);
}
