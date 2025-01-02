package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;

public interface StockService {
    StockDTO initializeStock(StockDTO stockDTO);
    StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate);
}
