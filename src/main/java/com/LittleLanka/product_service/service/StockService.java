package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;
import com.LittleLanka.product_service.dto.request.RequestUpdateStockDTO;
import com.LittleLanka.product_service.dto.response.ResponseUpdateStockDTO;

public interface StockService {
    StockDTO initializeStock(StockDTO stockDTO);
    StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate);

    ResponseUpdateStockDTO updateStockByOutletIdAndProductList(RequestUpdateStockDTO requestUpdateStockDTO);
}
