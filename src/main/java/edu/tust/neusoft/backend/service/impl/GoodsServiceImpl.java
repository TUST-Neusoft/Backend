package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.dto.ResetGoodsRequest;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    public boolean existsByGoodsNo(String goodsNo) {
        return goodsRepository.findByGoodsNo(goodsNo) != null;
    }

    @Override
    public Result getAllGoods() {
        List<Goods> goodsList = goodsRepository.findAll();
        return Result.success("获取成功", goodsList);
    }

    @Override
    @Transactional
    public Result resetGoodsMsg(ResetGoodsRequest resetGoodsRequest) {
        Goods goods = goodsRepository.findById(resetGoodsRequest.getGoodsId()).orElse(null);
        if (goods == null) {
            return Result.fail("未找到指定的商品");
        }

        if (!resetGoodsRequest.getGoods().isEmpty()) {
            ResetGoodsRequest.GoodsDto goodsDto = resetGoodsRequest.getGoods().get(0);
            goods.setGoodsName(goodsDto.getGoodsName());
            goods.setCategoryId(goodsDto.getCategoryId());
            goods.setGoodsIntroduce(goodsDto.getGoodsIntroduce());
            goods.setGoodsContent(goodsDto.getGoodsContent());
            goods.setGoodsState(goodsDto.getGoodsState());
            goods.setGoodsPicture(goodsDto.getGoodsPicture());
            goods.setGoodsMarketPrice(goodsDto.getGoodsMarketPrice());
            goods.setUpdateTime(LocalDateTime.now());
        }

        goodsRepository.save(goods);

        return Result.success("商品信息更新成功", goods);
    }
}
