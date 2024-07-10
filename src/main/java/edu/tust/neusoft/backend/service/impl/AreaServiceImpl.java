package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Area;
import edu.tust.neusoft.backend.model.dto.AddAreaRequest;
import edu.tust.neusoft.backend.repository.AreaRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Result addArea(AddAreaRequest addAreaRequest) {
        Area area = new Area();
        area.setAreaNo(addAreaRequest.getAreaNo());
        area.setAreaName(addAreaRequest.getAreaName());
        area.setParentId(0);
        area.setAreaType(0);
        area.setCreateTime(LocalDateTime.now());
        area.setUpdateTime(LocalDateTime.now());
        areaRepository.save(area);
        return Result.success("新增区域成功", area);
    }
}
