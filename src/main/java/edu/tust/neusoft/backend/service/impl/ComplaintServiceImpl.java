package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Complaint;
import edu.tust.neusoft.backend.model.dto.AddComplaintRequest;
import edu.tust.neusoft.backend.repository.ComplaintRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Result getMyComplaint(int userId) {
        List<Complaint> complaints = complaintRepository.findByUserId(userId);
        return Result.success("获取成功", complaints);
    }

    @Override
    public Result addComplaint(int userId, AddComplaintRequest request) {
        if (request == null) {
            return Result.fail("请求不能为空");
        }

        Complaint complaint = new Complaint();
        complaint.setUserId(userId);
        complaint.setComplaintContent(request.getComplaintContent());
        complaint.setComplaintStatus(0); // 假设默认状态为0
        complaint.setCreateTime(new Date());
        complaint.setUpdateTime(new Date());
        complaintRepository.save(complaint);
        return Result.success("报修记录已添加", complaint);
    }
}
