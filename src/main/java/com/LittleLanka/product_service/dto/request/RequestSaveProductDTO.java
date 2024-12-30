package com.LittleLanka.product_service.dto.request;

import com.LittleLanka.product_service.entity.enums.CategoryType;
import com.LittleLanka.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveProductDTO {

    private String productName;
    private CategoryType productCategory;
    private MeasuringUnitType measuringType;
    private Date updateDate;
    private Double price;

}
