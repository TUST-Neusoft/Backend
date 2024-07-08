package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "carts")
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "amount")
    private int amount;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", userId=" + userId +
                ", storeNo='" + storeNo + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
