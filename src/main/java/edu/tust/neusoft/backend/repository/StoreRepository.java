package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
