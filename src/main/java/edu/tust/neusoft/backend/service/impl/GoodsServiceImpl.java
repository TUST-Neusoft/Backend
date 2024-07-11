package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.GoodsStore;
import edu.tust.neusoft.backend.model.dto.ResetGoodsRequest;
import edu.tust.neusoft.backend.repository.GoodsPictureRepository;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.repository.GoodsStoreRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsStoreRepository goodsStoreRepository;
    private final GoodsPictureRepository goodsPictureRepository;
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsStoreRepository goodsStoreRepository, GoodsPictureRepository goodsPictureRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsStoreRepository = goodsStoreRepository;
        this.goodsPictureRepository = goodsPictureRepository;
    }

    @Override
    public Result getAllGoods() {
        List<Goods> goodsList = goodsRepository.findAll();
        return Result.success("获取成功", goodsList);
    }

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
    @Transactional
    public Result resetGoodsMsg(ResetGoodsRequest resetGoodsRequest) {
        logger.info("Entering resetGoodsMsg method with request: {}", resetGoodsRequest);

        Goods goods = goodsRepository.findById(resetGoodsRequest.getGoodsId()).orElse(null);
        if (goods == null) {
            logger.error("Goods not found for id: {}", resetGoodsRequest.getGoodsId());
            return Result.fail("未找到指定的商品");
        }

        logger.info("Goods found: {}", goods);
        if (!resetGoodsRequest.getGoods().isEmpty()) {
            ResetGoodsRequest.GoodsDto goodsDto = resetGoodsRequest.getGoods().get(0);

            logger.info("Updating goods_picture for goodsNo: {} to new goodsNo: {}", goods.getGoodsNo(), goodsDto.getGoodsNo());
            goodsPictureRepository.updateGoodsNo(goods.getGoodsNo(), goodsDto.getGoodsNo());

            goods.setGoodsNo(goodsDto.getGoodsNo());
            goods.setGoodsName(goodsDto.getGoodsName());
            goods.setCategoryId(goodsDto.getCategoryId());
            goods.setGoodsIntroduce(goodsDto.getGoodsIntroduce());
            goods.setGoodsContent(goodsDto.getGoodsContent());
            goods.setGoodState(goodsDto.getGoodsState());
            goods.setGoodsPicture(goodsDto.getGoodsPicture());
            goods.setGoodsPrice(goodsDto.getGoodsMarketPrice());
            goods.setUpdateTime(LocalDateTime.now());
            logger.info("Updated goods info: {}", goods);
        }

        goodsRepository.save(goods);
        logger.info("Goods saved successfully");

        return Result.success("商品信息更新成功", goods);
    }

    @Override
    public Result getGoodsStoreByGoodsNo(String goodsNo) {
        List<GoodsStore> goodsStoreList = goodsStoreRepository.findByGoodsNo(goodsNo);
        if (goodsStoreList.isEmpty()) {
            return Result.fail("未找到指定的商品库存信息");
        }
        return Result.success("获取成功", goodsStoreList);
    }
}

