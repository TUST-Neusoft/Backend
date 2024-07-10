package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Special;
import edu.tust.neusoft.backend.repository.SpecialRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.DiscountAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountAdminServiceImpl implements DiscountAdminService {
    private final SpecialRepository specialRepository;

    @Autowired
    public DiscountAdminServiceImpl(SpecialRepository specialRepository) {
        this.specialRepository = specialRepository;
    }

    @Override
    public Result getAllSpecialNotices() {
        List<Special> specials = specialRepository.findAll();
        return Result.success("获取成功", specials);
    }
}
