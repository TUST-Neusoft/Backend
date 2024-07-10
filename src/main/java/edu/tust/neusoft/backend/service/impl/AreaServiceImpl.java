package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Area;
import edu.tust.neusoft.backend.repository.AreaRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Result getAllAreas() {
        List<Area> areas = areaRepository.findAll();
        return Result.success("获取成功", areas);
    }
}
