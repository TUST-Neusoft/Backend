package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Goods findByGoodsNo(String goodsNo);
}
