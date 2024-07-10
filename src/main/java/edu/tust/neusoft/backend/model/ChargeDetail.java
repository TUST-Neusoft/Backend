package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "charge_detail")
public class ChargeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "charge_no")
    private String chargeNo;

    @Column(name = "charge_content")
    private String chargeContent;

    @Column(name = "charge_price")
    private double chargePrice;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
