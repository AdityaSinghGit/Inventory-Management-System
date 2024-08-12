package com.inventory.inventory.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "buynow")
public class BuyNowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String productId;
    private String quant;
    private String customerName;
    private String customerMobile;
    private LocalDateTime updatedOn;

    @PrePersist
    @PreUpdate
    public void updateTimeStamp(){
        this.updatedOn=LocalDateTime.now();
    }
}
