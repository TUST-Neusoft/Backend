package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayOrderRequest {
    @JsonProperty("orders_id")
    private String ordersId;

    @JsonProperty("wallet_password")
    private String walletPassword;
}
