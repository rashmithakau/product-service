package com.LittleLanka.product_service.dto.response;

import com.LittleLanka.product_service.entity.enums.CategoryType;
import com.LittleLanka.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSaveProductDTO {
    private Long productId;
    private String productName;
    private CategoryType productCategory;
    private boolean productStatus;
    private MeasuringUnitType measuringType;
    private Long priceUpdateId;
    private Date updateDate;
    private Double price;
}
