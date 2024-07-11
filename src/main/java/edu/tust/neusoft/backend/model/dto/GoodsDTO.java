package edu.tust.neusoft.backend.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GoodsDTO {
    private Long id;

    @JsonProperty("goods_no")
    private String goodsNo;

    private String goodsName;
    private int categoryId;
    private String goodsIntroduce;
    private String goodsContent;
    private int goodState;
    private String goodsPicture;
    private double goodsPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getters and setters
}

