package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @JsonProperty("order_no")
    private String orderNo;
    private List<GoodsRequest> goods;
}
