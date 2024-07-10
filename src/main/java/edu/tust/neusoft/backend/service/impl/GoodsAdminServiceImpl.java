package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.SpecialGoods;
import edu.tust.neusoft.backend.model.dto.SpecialGoodsResponse;
import edu.tust.neusoft.backend.repository.SpecialGoodsRepository;
import edu.tust.neusoft.backend.repository.GoodsRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsAdminServiceImpl implements GoodsAdminService {
    private final SpecialGoodsRepository specialGoodsRepository;
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsAdminServiceImpl(SpecialGoodsRepository specialGoodsRepository, GoodsRepository goodsRepository) {
        this.specialGoodsRepository = specialGoodsRepository;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Result getAllSpecialGoods() {
        List<SpecialGoods> specialGoodsList = specialGoodsRepository.findAll();
        List<SpecialGoodsResponse> responseList = specialGoodsList.stream().map(specialGoods -> {
            Goods goods = goodsRepository.findByGoodsNo(specialGoods.getGoodsNo());
            SpecialGoodsResponse response = new SpecialGoodsResponse();
            response.setId(specialGoods.getId());
            response.setSpecialId(specialGoods.getSpecialId());
            response.setCreateTime(specialGoods.getCreateTime());
            response.setUpdateTime(specialGoods.getUpdateTime());

            if (goods != null) {
                response.setGoodsNo(goods.getGoodsNo());
                response.setGoodsName(goods.getGoodsName());
                response.setCategoryId(goods.getCategoryId());
                response.setGoodsIntroduce(goods.getGoodsIntroduce());
                response.setGoodsContent(goods.getGoodsContent());
                response.setGoodsState(goods.getGoodState());
                response.setGoodsPicture(goods.getGoodsPicture());
                response.setGoodsMarketPrice(goods.getGoodsPrice());
                response.setGoodsCreateTime(goods.getCreateTime());
                response.setGoodsUpdateTime(goods.getUpdateTime());
            }

            return response;
        }).collect(Collectors.toList());

        return Result.success("获取成功", responseList);
    }

    @Override
    public Result deleteGoodsByGoodsNo(String goodsNo) {
        List<SpecialGoods> specialGoodsList = specialGoodsRepository.findByGoodsNo(goodsNo);
        if (specialGoodsList.isEmpty()) {
            return Result.fail("未找到指定的商品");
        }
        specialGoodsRepository.deleteAll(specialGoodsList);
        return Result.success("删除成功", null);
    }

    @Override
    public Result addGoodsByGoodsNo(String goodsNo) {
        SpecialGoods specialGoods = new SpecialGoods();
        specialGoods.setSpecialId(1L);
        specialGoods.setGoodsNo(goodsNo);
        specialGoods.setCreateTime(LocalDateTime.now());
        specialGoods.setUpdateTime(LocalDateTime.now());
        specialGoodsRepository.save(specialGoods);
        return Result.success("添加成功", specialGoods);
    }
}
