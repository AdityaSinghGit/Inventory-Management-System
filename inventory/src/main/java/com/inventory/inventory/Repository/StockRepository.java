package com.inventory.inventory.Repository;

import java.util.Optional;
// import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.inventory.Entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,String>{
    Optional<StockEntity> findByProductId(String productId);
}
    // StockRepository stockByproductId();
