package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class GoodsFavoritesController {
    private final GoodsFavoritesService goodsFavoritesService;

    @Autowired
    public GoodsFavoritesController(GoodsFavoritesService goodsFavoritesService) {
        this.goodsFavoritesService = goodsFavoritesService;
    }

    @GetMapping("/getFavoritesNum")
    public Result getFavoritesCountByGoodsNo(@RequestParam("goods_no") String goodsNo) {
        if (goodsNo == null || goodsNo.isEmpty()) {
            return Result.fail("goods_no 参数不能为空");
        }
        return goodsFavoritesService.countFavoritesByGoodsNo(goodsNo);
    }

    @GetMapping("/getMyFavorites")
    public Result getUserFavorites(@RequestParam("user_id") int userId) {
        return goodsFavoritesService.getFavoritesByUserId(userId);
    }

}
