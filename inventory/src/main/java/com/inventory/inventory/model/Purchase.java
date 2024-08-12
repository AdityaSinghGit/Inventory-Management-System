package com.inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private String id;
    private String supplierName;
    private String supplierMobile;
    private String productId;
    private String mrp;
    private String rate;
    private String Stock;
    private String varient;
    private LocalDateTime updatedOn;
}
