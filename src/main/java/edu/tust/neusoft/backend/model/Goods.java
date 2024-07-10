package edu.tust.neusoft.backend.model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "goods_introduce")
    private String goodsIntroduce;

    @Column(name = "goods_content")
    private String goodsContent;

    @Column(name = "goods_state")
    private int goodState;

    @Column(name = "goods_picture")
    private String goodsPicture;

    @Column(name = "goods_market_price")
    private Double goodsPrice;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
