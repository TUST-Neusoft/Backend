package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.AddGoodsCategoryRequest;
import edu.tust.neusoft.backend.model.dto.ResetCategoryRequest;
import edu.tust.neusoft.backend.response.Result;

public interface GoodsCategoryService {
    Result getAllGoodsCategories();
    Result addGoodsCategory(AddGoodsCategoryRequest addGoodsCategoryRequest);
    Result resetCategory(ResetCategoryRequest resetCategoryRequest);
}
