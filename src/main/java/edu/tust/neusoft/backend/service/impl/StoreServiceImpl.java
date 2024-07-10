package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Store;
import edu.tust.neusoft.backend.repository.StoreRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Result getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return Result.success("获取成功", stores);
    }
}
