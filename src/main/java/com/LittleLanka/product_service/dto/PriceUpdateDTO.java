package com.LittleLanka.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDTO {

    private Long productUpdateId;
    private Long productId;
    private Date productUpdateDate;
    private Double productUpdatePrice;

}
