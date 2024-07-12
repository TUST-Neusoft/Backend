package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResetCategoryRequest {
    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("category")
    private List<CategoryInfo> categoryInfo;

    @Getter
    @Setter
    public static class CategoryInfo {
        @JsonProperty("category_name")
        private String categoryName;
        @JsonProperty("category_type")
        private int categoryType;
    }
}
