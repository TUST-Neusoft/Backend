package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    List<Visitor> findByUserId(Long userId);
}
