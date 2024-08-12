package com.inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyNow {
    private String id;
    private String productId;
    private String quant;
    private String customerName;
    private String customerMobile;
    private LocalDateTime updatedOn;
}
