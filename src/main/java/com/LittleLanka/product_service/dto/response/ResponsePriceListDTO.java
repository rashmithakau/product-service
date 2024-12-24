package com.LittleLanka.product_service.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsePriceListDTO {
    private Long productId;
    private Double price;

    // Constructor to accept productId and price
    public ResponsePriceListDTO(Long productId, Double price) {
        this.productId = productId;
        this.price = price;
    }
}
