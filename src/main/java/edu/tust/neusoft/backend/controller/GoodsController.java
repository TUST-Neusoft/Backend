package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/getAll")
    public Result getGoodsByCategoryId() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/getDetail")
    public Result getGoodsByGoodsNo(String goodsNo) {
        if (goodsNo == null || goodsNo.isEmpty()) {
            return Result.fail("goodsNo参数不能为空");
        }
        return goodsService.getGoodsByGoodsNo(goodsNo);
    }

    @GetMapping("/search")
    public Result searchGoodsByGoodsName(String goodsName) {
        if (goodsName == null || goodsName.isEmpty()) {
            return Result.fail("goodsName参数不能为空");
        }
        return goodsService.searchGoodsByGoodsName(goodsName);
    }
}
