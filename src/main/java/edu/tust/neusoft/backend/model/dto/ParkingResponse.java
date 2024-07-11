package edu.tust.neusoft.backend.model.dto;

import edu.tust.neusoft.backend.model.ParkingBindAdmin;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ParkingResponse {
    private Long id;
    private Long userId;
    private String parkingName;
    private String parkingType;
    private int parkingStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<ParkingBindAdmin> parkingBindAdmins;
}
