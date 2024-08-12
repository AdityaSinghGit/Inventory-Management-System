package com.inventory.inventory.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; 
    
    private String productName;

    private String category;

    private String productVarient;
    private String mrp;
    private String sellingPrice;
    private String description;
    private LocalDateTime updatedOn;

    
    @PrePersist
    @PreUpdate
    public void updateTimeStamp(){
        this.updatedOn=LocalDateTime.now();
    }
}
