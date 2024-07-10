package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.Date;
=======
import java.time.LocalDateTime;
>>>>>>> origin/ZhangWang

@Data
@Entity
@Table(name = "portal_wallet_log")
public class WalletLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

<<<<<<< HEAD
    @Column(name = "wallet_id")
    private int walletId;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private String type;

    @Column(name = "state")
    private String state;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
=======
    @Column(name = "wallet_id", nullable = false)
    private int walletId;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "state", nullable = false)
    private int state;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
>>>>>>> origin/ZhangWang
}
