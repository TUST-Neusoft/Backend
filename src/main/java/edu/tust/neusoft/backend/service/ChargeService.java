package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface ChargeService {
    Result getMyCharge(int userId);
}
