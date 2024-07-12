package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.AddComplaintRequest;
import edu.tust.neusoft.backend.response.Result;

public interface ComplaintService {
    Result getMyComplaint(int userId);
    Result addComplaint(int userId, AddComplaintRequest request);
}
