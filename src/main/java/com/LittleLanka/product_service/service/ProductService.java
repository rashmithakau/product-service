package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.*;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(RequestSaveProductDto requestSaveProductDTO);

    Double getPriceByDateAndProductId(String date, Long id);

    List<ResponsePriceListDTO> getPriceListByDate(String date);

    StockDTO initializeStock(StockDTO stockDTO);

    PriceUpdateDTO updatePrice(RequestPriceUpdateDto requestPriceUpdateDto);

    StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate);

    List<ResponsePriceListDTO> getPriceListByDateAndProductIdList(RequestDateAndPriceListDTO requestDateAndPriceListDTO);
}
