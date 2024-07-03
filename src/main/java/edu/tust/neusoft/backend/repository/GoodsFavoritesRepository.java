package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.GoodsFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsFavoritesRepository extends JpaRepository<GoodsFavorites, Integer> {
    long countByGoodsNo(String goodsNo);
    List<GoodsFavorites> findByUserId(int userId);
}
