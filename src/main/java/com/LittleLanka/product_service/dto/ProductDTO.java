package com.LittleLanka.product_service.dto;

import com.LittleLanka.product_service.entity.enums.CatagoryType;
import com.LittleLanka.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private CatagoryType productCatagory;
    private boolean productStatus;
    private MeasuringUnitType productMeasuringUnitType = MeasuringUnitType.NUMBER;
}