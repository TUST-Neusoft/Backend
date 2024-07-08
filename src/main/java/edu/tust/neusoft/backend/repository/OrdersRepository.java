package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId(int userId);
}
