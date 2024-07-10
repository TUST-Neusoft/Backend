package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CartsRepository extends JpaRepository<Carts, Integer> {
    List<Carts> findByUserId(Integer userId);
    @Transactional
    void deleteByUserId(int userId);

    @Transactional
    void deleteByUserIdAndGoodsNoAndStoreNo(int userId, String goodsNo, String storeNo);

    Optional<Carts> findByUserIdAndGoodsNoAndStoreNo(int userId, String goodsNo, String storeNo);
}
