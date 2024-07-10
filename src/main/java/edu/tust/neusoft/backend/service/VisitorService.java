package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.AddVisitorRequest;
import edu.tust.neusoft.backend.response.Result;

public interface VisitorService {
    Result getMyVisitor(int userId);
    Result addVisitor(int userId, AddVisitorRequest request);
}
