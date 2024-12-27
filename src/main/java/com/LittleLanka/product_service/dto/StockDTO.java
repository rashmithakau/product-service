package com.LittleLanka.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDTO {
    private Long stockId;
    private Long productId;
    private Long outletId;
    private int stockQuantity;
}
