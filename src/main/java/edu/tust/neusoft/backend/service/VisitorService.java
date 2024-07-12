package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.AddVisitorRequest;
import edu.tust.neusoft.backend.response.Result;

public interface VisitorService {
    Result getMyVisitor(Long userId);
    Result addVisitor(Long userId, AddVisitorRequest request);
}
