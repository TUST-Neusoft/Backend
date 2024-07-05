package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreNo(String storeNo);
}

