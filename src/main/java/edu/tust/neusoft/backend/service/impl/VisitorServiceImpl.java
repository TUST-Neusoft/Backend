package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Visitor;
import edu.tust.neusoft.backend.model.dto.AddVisitorRequest;
import edu.tust.neusoft.backend.repository.VisitorRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Result getMyVisitor(Long userId) {
        List<Visitor> visitors = visitorRepository.findByUserId(userId);
        return Result.success("获取成功", visitors);
    }

    @Override
    public Result addVisitor(Long userId, AddVisitorRequest request) {
        if (request == null) {
            return Result.fail("请求不能为空");
        }

        Visitor visitor = new Visitor();
        visitor.setUserId(userId);
        visitor.setVisitorObjective(request.getVisitorObjective());
        try {
            Date visitorTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getVisitorTime());
            visitor.setVisitorTime(visitorTime);
        } catch (ParseException e) {
            return Result.fail("日期格式错误");
        }
        visitor.setVisitorStatus(1); // 使用整数表示状态，例如 1 表示 active
        visitor.setCreateTime(new Date());
        visitor.setUpdateTime(new Date());
        visitorRepository.save(visitor);
        return Result.success("访客记录已添加", visitor);
    }
}
