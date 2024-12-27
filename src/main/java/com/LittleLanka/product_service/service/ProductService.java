package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestInitializeStockDto;
import com.LittleLanka.product_service.dto.request.RequestPriceUpdateDto;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDto;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(RequestSaveProductDto requestSaveProductDTO);

    Double getPriceByDateAndProductId(String date, Long id);

    List<ResponsePriceListDTO> getPriceListByDate(String date);

    StockDTO initializeStock(StockDTO stockDTO);

    PriceUpdateDTO updatePrice(RequestPriceUpdateDto requestPriceUpdateDto);

    StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate);
}
