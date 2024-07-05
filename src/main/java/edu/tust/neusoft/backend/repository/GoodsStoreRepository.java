package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.GoodsStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsStoreRepository extends JpaRepository<GoodsStore, Long> {
    Optional<GoodsStore> findByGoodsNoAndStoreNo(String goodsNo, String storeNo);
}

