package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Result getGoodsByCategoryId(int categoryId) {
        List<Goods> goodsList = goodsRepository.findByCategoryId(categoryId);
        if (goodsList.isEmpty()) {
            return Result.fail("获取失败");
        }
        return Result.success("获取成功", goodsList);
    }

    @Override
    public Result getGoodsByGoodsNo(String goodsNo) {
        Goods goods = goodsRepository.findByGoodsNo(goodsNo);
        if (goods == null) {
            return Result.fail("获取失败");
        }
        return Result.success("获取成功", goods);
    }

    @Override
    public Result searchGoodsByGoodsName(String goodsName) {
        List<Goods> goodsList = goodsRepository.findByGoodsNameContaining(goodsName);
        if (goodsList.isEmpty()) {
            return Result.fail("获取失败");
        }
        return Result.success("获取成功", goodsList);
    }
}
