package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.AddAreaRequest;
import edu.tust.neusoft.backend.response.Result;

public interface AreaService {
    Result getAllAreas();
    Result addArea(AddAreaRequest addAreaRequest);
}
