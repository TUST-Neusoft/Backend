package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.AddAreaRequest;
import edu.tust.neusoft.backend.model.dto.AddGoodsCategoryRequest;
import edu.tust.neusoft.backend.model.dto.ResetCategoryRequest;
import edu.tust.neusoft.backend.model.dto.ResetGoodsPriceAndStorageRequest;
import edu.tust.neusoft.backend.model.dto.ResetGoodsRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.AreaService;
import edu.tust.neusoft.backend.service.GoodsCategoryService;
import edu.tust.neusoft.backend.service.GoodsService;
import edu.tust.neusoft.backend.service.StoreService;
import edu.tust.neusoft.backend.service.GoodsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manage")
public class ManageController {
    private final GoodsCategoryService goodsCategoryService;
    private final GoodsService goodsService;
    private final AreaService areaService;
    private final StoreService storeService;
    private final GoodsStoreService goodsStoreService;

    @Autowired
    public ManageController(GoodsCategoryService goodsCategoryService, GoodsService goodsService, AreaService areaService, StoreService storeService, GoodsStoreService goodsStoreService) {
        this.goodsCategoryService = goodsCategoryService;
        this.goodsService = goodsService;
        this.areaService = areaService;
        this.storeService = storeService;
        this.goodsStoreService = goodsStoreService;
    }

    @GetMapping("/GetGoods")
    public Result getAllGoodsCategories() {
        return goodsCategoryService.getAllGoodsCategories();
    }

    @GetMapping("/GetGoodsMess")
    public Result getAllGoods() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/GetArea")
    public Result getAllAreas() {
        return areaService.getAllAreas();
    }

    @GetMapping("/GetStore")
    public Result getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/StoreGoods")
    public Result getGoodsByStoreNo(@RequestParam String storeNo) {
        return goodsStoreService.getGoodsByStoreNo(storeNo);
    }

    @PostMapping("/ResetGoodsPriceAndStorage")
    public Result resetGoodsPriceAndStorage(@RequestBody ResetGoodsPriceAndStorageRequest resetGoodsPriceAndStorageRequest) {
        return goodsStoreService.resetGoodsPriceAndStorage(resetGoodsPriceAndStorageRequest);
    }

    @PostMapping("/AddGoodsCategory")
    public Result addGoodsCategory(@RequestBody AddGoodsCategoryRequest addGoodsCategoryRequest) {
        return goodsCategoryService.addGoodsCategory(addGoodsCategoryRequest);
    }

    @PostMapping("/ResetCategory")
    public Result resetCategory(@RequestBody ResetCategoryRequest resetCategoryRequest) {
        return goodsCategoryService.resetCategory(resetCategoryRequest);
    }

    @PostMapping("/ResetGoodsMsg")
    public Result resetGoodsMsg(@RequestBody ResetGoodsRequest resetGoodsRequest) {
        return goodsService.resetGoodsMsg(resetGoodsRequest);
    }

    @PostMapping("/AddArea")
    public Result addArea(@RequestBody AddAreaRequest addAreaRequest) {
        return areaService.addArea(addAreaRequest);
    }
}
