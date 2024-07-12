package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParkingBindRequest {
    @JsonProperty("parking_id")
    private int parkingId;

    @JsonProperty("license_plate")
    private String licensePlate;
}
