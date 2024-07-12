package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsService;
import edu.tust.neusoft.backend.service.GoodsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Result getGoodsByGoodsNo(@RequestParam String goodsNo) {
        if (goodsNo == null || goodsNo.isEmpty()) {
            return Result.fail("goodsNo参数不能为空");
        }
        return goodsService.getGoodsByGoodsNo(goodsNo);
    }

    @GetMapping("/search")
    public Result searchGoodsByGoodsName(@RequestParam String goodsName) {
        if (goodsName == null || goodsName.isEmpty()) {
            return Result.fail("goodsName参数不能为空");
        }
        return goodsService.searchGoodsByGoodsName(goodsName);
    }

    @PostMapping("/getGoodsStore")
    public Result getGoodsStoreByGoodsNo(@RequestBody Map<String, String> request) {
        String goodsNo = request.get("goodsNo");
        if (goodsNo == null || goodsNo.isEmpty()) {
            return Result.fail("goodsNo参数不能为空");
        }
        return goodsService.getGoodsStoreByGoodsNo(goodsNo);
    }
}

