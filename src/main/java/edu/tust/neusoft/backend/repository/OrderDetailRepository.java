package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderNo(String orderNo);
}
