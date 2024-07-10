package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.DiscountAdminService;
import edu.tust.neusoft.backend.service.GoodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discountAdmin")
public class DiscountAdminController {
    private final DiscountAdminService discountAdminService;
    private final GoodsAdminService goodsAdminService;

    @Autowired
    public DiscountAdminController(DiscountAdminService discountAdminService, GoodsAdminService goodsAdminService) {
        this.discountAdminService = discountAdminService;
        this.goodsAdminService = goodsAdminService;
    }

    @GetMapping("/getAllnotice")
    public Result getAllSpecialNotices() {
        return discountAdminService.getAllSpecialNotices();
    }

    @GetMapping("/InquireGoods")
    public Result getAllSpecialGoods() {
        return goodsAdminService.getAllSpecialGoods();
    }

    @DeleteMapping("/deletegoods")
    public Result deleteGoods(@RequestParam("goods_no") String goodsNo) {
        return goodsAdminService.deleteGoodsByGoodsNo(goodsNo);
    }

    @PostMapping("/addgoods")
    public Result addGoods(@RequestParam("goods_no") String goodsNo) {
        return goodsAdminService.addGoodsByGoodsNo(goodsNo);
    }
}
