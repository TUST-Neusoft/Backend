package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface RepairService {
    Result getAllComplaintsByUserId(Long userId);
    Result changeComplaintStatus(Long id);
}
