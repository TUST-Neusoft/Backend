package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.VisitorAdmin;
import edu.tust.neusoft.backend.repository.VisitorRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.VisitorRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorRecordsServiceImpl implements VisitorRecordsService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorRecordsServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Result getAllVisitorRecordsByUserId(Long userId) {
        List<VisitorAdmin> visitorAdminList = visitorRepository.findByUserId(userId);
        if (visitorAdminList.isEmpty()) {
            return Result.fail("没有找到访客记录");
        }
        return Result.success("获取成功", visitorAdminList);
    }

    @Override
    public Result changeVisitorStatus(Long id) {
        Optional<VisitorAdmin> optionalVisitor = visitorRepository.findById(id);
        if (optionalVisitor.isPresent()) {
            VisitorAdmin visitorAdmin = optionalVisitor.get();
            visitorAdmin.setVisitorStatus(1); // 修改访客状态为1
            visitorAdmin.setUpdateTime(LocalDateTime.now());
            visitorRepository.save(visitorAdmin);
            return Result.success("已来访", visitorAdmin);
        } else {
            return Result.fail("未找到指定的访客记录");
        }
    }
}
