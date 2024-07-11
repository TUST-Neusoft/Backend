package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.ComplaintAdmin;
import edu.tust.neusoft.backend.repository.ComplaintAdminRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {
    private final ComplaintAdminRepository complaintAdminRepository;

    @Autowired
    public RepairServiceImpl(ComplaintAdminRepository complaintAdminRepository) {
        this.complaintAdminRepository = complaintAdminRepository;
    }

    @Override
    public Result getAllComplaintsByUserId(Long userId) {
        List<ComplaintAdmin> complaints = complaintAdminRepository.findByUserId(userId);
        if (complaints.isEmpty()) {
            return Result.fail("没有找到投诉记录");
        }
        return Result.success("获取成功", complaints);
    }

    @Override
    public Result changeComplaintStatus(Long id) {
        Optional<ComplaintAdmin> optionalComplaint = complaintAdminRepository.findById(id);
        if (optionalComplaint.isPresent()) {
            ComplaintAdmin complaint = optionalComplaint.get();
            complaint.setComplaintStatus(1); // 修改投诉状态为1
            complaint.setUpdateTime(LocalDateTime.now());
            complaintAdminRepository.save(complaint);
            return Result.success("已处理", complaint);
        } else {
            return Result.fail("未找到投诉记录");
        }
    }
}
