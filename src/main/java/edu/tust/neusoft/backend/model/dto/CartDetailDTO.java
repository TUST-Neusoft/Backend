package edu.tust.neusoft.backend.model.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CartDetailDTO {
    private Long id;
    private int userId;
    private StoreDTO storeNo;
    private GoodsDTO goodsNo;
    private int amount;
    private Date createTime;
    private Date updateTime;
    private Double price;
    private Integer stock;

    // Getters and setters
}
