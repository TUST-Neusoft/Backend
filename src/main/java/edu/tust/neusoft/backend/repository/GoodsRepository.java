package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    List<Goods> findByCategoryId(int categoryId);
    Goods findByGoodsNo(String goodsNo);
    List<Goods> findByGoodsNameContaining(String goodsName);
}
