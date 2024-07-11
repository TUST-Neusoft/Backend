package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Carts;
import edu.tust.neusoft.backend.model.GoodsStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsStoreRepository extends JpaRepository<GoodsStore, Long> {
    List<GoodsStore> findByStoreNo(String storeNo);
//    GoodsStore findByGoodsNoAndStoreNo(String storeNo, String goodsNo);
    Optional<GoodsStore> findByGoodsNoAndStoreNo(String storeNo, String goodsNo);
}
