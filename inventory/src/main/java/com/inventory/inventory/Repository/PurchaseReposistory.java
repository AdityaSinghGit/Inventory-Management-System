package com.inventory.inventory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.inventory.Entity.PurchaseEntity;

@Repository
public interface PurchaseReposistory extends JpaRepository<PurchaseEntity,String>{
    
}
