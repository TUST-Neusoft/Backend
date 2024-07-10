package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Complaint;
import edu.tust.neusoft.backend.repository.ComplaintRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {
    private final ComplaintRepository complaintRepository;

    @Autowired
    public RepairServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Result getAllComplaintsByUserId(Long userId) {
        List<Complaint> complaints = complaintRepository.findByUserId(userId);
        if (complaints.isEmpty()) {
            return Result.fail("没有找到投诉记录");
        }
        return Result.success("获取成功", complaints);
    }

    @Override
    public Result changeComplaintStatus(Long id) {
        Optional<Complaint> optionalComplaint = complaintRepository.findById(id);
        if (optionalComplaint.isPresent()) {
            Complaint complaint = optionalComplaint.get();
            complaint.setComplaintStatus(1); // 修改投诉状态为1
            complaint.setUpdateTime(LocalDateTime.now());
            complaintRepository.save(complaint);
            return Result.success("已处理", complaint);
        } else {
            return Result.fail("未找到投诉记录");
        }
    }
}
