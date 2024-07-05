package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartsRepository extends JpaRepository<Carts, Integer> {
    List<Carts> findByUserId(int userId);
}
