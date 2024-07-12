package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.GoodsCategory;
import edu.tust.neusoft.backend.model.dto.AddGoodsCategoryRequest;
import edu.tust.neusoft.backend.model.dto.ResetCategoryRequest;
import edu.tust.neusoft.backend.repository.GoodsCategoryRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private final GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    public GoodsCategoryServiceImpl(GoodsCategoryRepository goodsCategoryRepository) {
        this.goodsCategoryRepository = goodsCategoryRepository;
    }

    @Override
    public Result getAllGoodsCategories() {
        List<GoodsCategory> goodsCategoryList = goodsCategoryRepository.findAll();
        if (goodsCategoryList.isEmpty()) {
            return Result.fail("获取失败");
        }

        return Result.success("获取成功", goodsCategoryList);
    }

    @Override
    public Result addGoodsCategory(AddGoodsCategoryRequest addGoodsCategoryRequest) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategoryName(addGoodsCategoryRequest.getCategoryName());
        goodsCategory.setCategoryType(addGoodsCategoryRequest.getCategoryType());
        goodsCategory.setParentId(0);
        goodsCategory.setCreateTime(new Date());
        goodsCategory.setUpdateTime(new Date());
        goodsCategoryRepository.save(goodsCategory);

        return Result.success("类别新增成功", goodsCategory);
    }

    @Override
    public Result resetCategory(ResetCategoryRequest resetCategoryRequest) {
        Optional<GoodsCategory> optionalCategory = goodsCategoryRepository.findById(resetCategoryRequest.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return Result.fail("未找到指定类别");
        }

        GoodsCategory goodsCategory = optionalCategory.get();
        ResetCategoryRequest.CategoryInfo categoryInfo = resetCategoryRequest.getCategoryInfo().get(0);
        goodsCategory.setCategoryName(categoryInfo.getCategoryName());
        goodsCategory.setCategoryType(categoryInfo.getCategoryType());
        goodsCategory.setUpdateTime(new Date());
        goodsCategoryRepository.save(goodsCategory);

        return Result.success("类别信息更新成功", goodsCategory);
    }
}
