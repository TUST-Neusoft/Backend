package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface PaymentService {
    Result getAllChargesByUserId(Long userId);
    Result getChargeDetailsByChargeNo(String chargeNo);
}
