package com.inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private String id;
    private String ProductId;
    private String quantity;
    private LocalDateTime updatedon;
}
