package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "goods_store")
public class GoodsStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "goods_price")
    private Double price;

    @Column(name = "goods_stock")
    private double stock;

    // 其他字段根据需要添加
}

