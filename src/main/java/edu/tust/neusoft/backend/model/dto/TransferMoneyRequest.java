package edu.tust.neusoft.backend.model.dto;

import lombok.Data;

@Data
public class TransferMoneyRequest {
    private String phone;
    private double money;
}
