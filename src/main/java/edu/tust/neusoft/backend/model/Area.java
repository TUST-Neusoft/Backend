package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "area_no")
    private String areaNo;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "area_type")
    private int areaType;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
