package com.inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.inventory.inventory.Entity.CategoryEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id; 
    private String productName;
    private String category;
    private String productVarient;
    private String mrp;
    private String sellingPrice;
    private String description;
    private LocalDateTime updatedOn;
}
