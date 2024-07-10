package edu.tust.neusoft.backend.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GoodsDTO {
    private Long id;
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

