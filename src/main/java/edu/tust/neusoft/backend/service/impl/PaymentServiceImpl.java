package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.ChargeAdmin;
import edu.tust.neusoft.backend.model.ChargeDetailAdmin;
import edu.tust.neusoft.backend.repository.ChargeRepository;
import edu.tust.neusoft.backend.repository.ChargeDetailRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final ChargeRepository chargeRepository;
    private final ChargeDetailRepository chargeDetailRepository;

    @Autowired
    public PaymentServiceImpl(ChargeRepository chargeRepository, ChargeDetailRepository chargeDetailRepository) {
        this.chargeRepository = chargeRepository;
        this.chargeDetailRepository = chargeDetailRepository;
    }

    @Override
    public Result getAllChargesByUserId(Long userId) {
        List<ChargeAdmin> charges = chargeRepository.findByUserId(userId);
        if (charges.isEmpty()) {
            return Result.fail("没有找到收费记录");
        }
        return Result.success("获取成功", charges);
    }

    @Override
    public Result getChargeDetailsByChargeNo(String chargeNo) {
        List<ChargeDetailAdmin> chargeDetails = chargeDetailRepository.findByChargeNo(chargeNo);
        if (chargeDetails.isEmpty()) {
            return Result.fail("没有找到收费详情记录");
        }
        return Result.success("获取成功", chargeDetails);
    }
}
