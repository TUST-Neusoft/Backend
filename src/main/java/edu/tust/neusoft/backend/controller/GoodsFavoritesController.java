package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.GoodsFavorites;
import edu.tust.neusoft.backend.model.dto.AddFavoriteRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsFavoritesService;
import edu.tust.neusoft.backend.service.GoodsService;
import edu.tust.neusoft.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/favorites")
public class GoodsFavoritesController {
    private final GoodsFavoritesService goodsFavoritesService;
    private final UserService userService;
    private final GoodsService goodsService;

    @Autowired
    public GoodsFavoritesController(GoodsFavoritesService goodsFavoritesService, UserService userService, GoodsService goodsService) {
        this.goodsFavoritesService = goodsFavoritesService;
        this.userService = userService;
        this.goodsService = goodsService;
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

    @PostMapping("/addFavorites")
    public Result addFavorite(@RequestBody AddFavoriteRequest request) {
        if (request.getGoodsNo() == null || request.getGoodsNo().isEmpty() || request.getUserId() == 0) {
            return Result.fail("goods_no 和 user_id 参数不能为空");
        }

        // 检查 user_id 是否存在
        if (!userService.existsById(request.getUserId())) {
            return Result.fail("用户ID不存在");
        }

        // 检查 goods_no 是否存在
        if (!goodsService.existsByGoodsNo(request.getGoodsNo())) {
            return Result.fail("商品编号不存在");
        }

        GoodsFavorites goodsFavorites = new GoodsFavorites();
        goodsFavorites.setGoodsNo(request.getGoodsNo());
        goodsFavorites.setUserId(request.getUserId());
        goodsFavorites.setCreateTime(LocalDateTime.now());
        return goodsFavoritesService.addFavorite(goodsFavorites);
    }

    @GetMapping("/deleteFavorites")
    public Result deleteFavorite(@RequestParam("favorites_id") int favoritesId) {
        boolean exists = goodsFavoritesService.existsById(favoritesId);
        if (!exists) {
            return Result.fail("收藏记录不存在");
        }
        goodsFavoritesService.deleteById(favoritesId);
        return Result.success("删除成功", null);
    }
}
