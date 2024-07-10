package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "portal_wallet_log")
public class WalletLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

}
