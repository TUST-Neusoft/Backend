package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGoodsCategoryRequest {
    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_type")
    private int categoryType;
}
