package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.SpecialGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialGoodsRepository extends JpaRepository<SpecialGoods, Long> {
    List<SpecialGoods> findByGoodsNo(String goodsNo);
    void deleteByGoodsNo(String goodsNo);
}
