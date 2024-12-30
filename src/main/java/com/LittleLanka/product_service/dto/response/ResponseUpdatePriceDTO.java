package com.LittleLanka.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUpdatePriceDTO {
    private Long priceUpdateId;
    private Date updateDate;
    private Double price;
    private Long productId;
    private String productName;
}
