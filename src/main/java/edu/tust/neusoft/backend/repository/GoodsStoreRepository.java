package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.GoodsStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsStoreRepository extends JpaRepository<GoodsStore, Integer> {
    List<GoodsStore> findByStoreNo(String storeNo);
    GoodsStore findByGoodsNoAndStoreNo(String storeNo, String goodsNo);
}
