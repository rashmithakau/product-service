package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;

import java.util.Date;
import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO);

    Double getPriceByDateAndProductId(String date, Long id);

    List<ResponsePriceListDTO> getPriceListByDate(String date);

    StockDTO initializeStock(StockDTO stockDTO);
}
