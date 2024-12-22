package com.LittleLanka.product_service.dto.request;

import com.LittleLanka.product_service.entity.enums.CatagoryType;
import com.LittleLanka.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveProductDTO {
    private String productName;
    private CatagoryType productCatagory;
    private MeasuringUnitType productMeasuringUnitType = MeasuringUnitType.NUMBER;

}
