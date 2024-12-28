package com.LittleLanka.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStockUpdateDto {
    private Long stockId;
    private double stockQuantity;
}
