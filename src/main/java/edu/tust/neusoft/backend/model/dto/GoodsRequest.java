package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoodsRequest {
    @JsonProperty("goods_no")
    private String goodsNo;

    @JsonProperty("store_no")
    private String storeNo;
}
