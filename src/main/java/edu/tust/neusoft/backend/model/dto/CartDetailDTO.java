package edu.tust.neusoft.backend.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CartDetailDTO {
    private Integer id;

    @JsonProperty("user_id")
    private Long userId;

    private StoreDTO storeNo;

    private GoodsDTO goodsNo;

    private int amount;
    private Date createTime;
    private Date updateTime;
    private Double price;
    private Integer stock;

    // Getters and setters
}
