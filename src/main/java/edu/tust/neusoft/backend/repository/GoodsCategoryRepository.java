package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Integer> {
}
