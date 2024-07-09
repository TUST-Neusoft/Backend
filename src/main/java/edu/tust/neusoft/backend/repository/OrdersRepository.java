package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId(int userId);
    Optional<Orders> findByOrderNo(String orderNo);
    boolean existsByOrderNoAndUserId(String orderNo, int userId);
}
