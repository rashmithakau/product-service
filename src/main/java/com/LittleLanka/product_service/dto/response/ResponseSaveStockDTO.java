package com.LittleLanka.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSaveStockDTO {
    private Long stockId;
    private Long outletId;
    private int stockQuantity;
    private Long productId;
    private String productName;
}
