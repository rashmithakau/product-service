package com.LittleLanka.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceUpdateDTO {

    private Long priceUpdateId;
    private Long productId;
    private Date priceUpdateDate;
    private Double price;

}
