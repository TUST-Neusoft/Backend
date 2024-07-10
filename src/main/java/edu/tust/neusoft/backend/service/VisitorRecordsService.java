package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface VisitorRecordsService {
    Result getAllVisitorRecordsByUserId(Long userId);
    Result changeVisitorStatus(Long id);
}
