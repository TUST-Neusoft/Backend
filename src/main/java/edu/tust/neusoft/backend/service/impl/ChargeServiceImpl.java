package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Charge;
import edu.tust.neusoft.backend.repository.ChargeRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    private final ChargeRepository chargeRepository;

    @Autowired
    public ChargeServiceImpl(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public Result getMyCharge(int userId) {
        List<Charge> charges = chargeRepository.findByUserId(userId);
        return Result.success("获取成功", charges);
    }
}
