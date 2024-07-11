package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "visitor_objective")
    private String visitorObjective;

    @Column(name = "visitor_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitorTime;

    @Column(name = "visitor_status")
    private int visitorStatus; // 使用 int 类型

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
