package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    List<Complaint> findByUserId(int userId);
}
