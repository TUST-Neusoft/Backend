package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.GoodsPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GoodsPictureRepository extends JpaRepository<GoodsPicture, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE GoodsPicture gp SET gp.goodsNo = :newGoodsNo WHERE gp.goodsNo = :oldGoodsNo")
    void updateGoodsNo(@Param("oldGoodsNo") String oldGoodsNo, @Param("newGoodsNo") String newGoodsNo);
}
