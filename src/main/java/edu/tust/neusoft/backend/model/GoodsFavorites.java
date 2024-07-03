package edu.tust.neusoft.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Entity
@Table(name = "goods_favorites")
public class GoodsFavorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
