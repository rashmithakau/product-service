package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveStockDTO;
import com.LittleLanka.product_service.dto.request.RequestUpdatePriceTO;
import com.LittleLanka.product_service.dto.response.*;

import java.util.List;

public interface ProductService {
    ResponseSaveProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO);

    ResponseSaveStockDTO saveStock(RequestSaveStockDTO requestSaveStockDTO);

    ResponseUpdatePriceDTO updatePrice(RequestUpdatePriceTO requestUpdatePriceTO);

    ResponseUpdateStockDTO updateStockByOutletIdAndProductId(Long outletId, Long productId, int qty);

    Double getPriceByDateAndProductId(String date, Long productId);

    List<ResponsePriceListDTO> getPriceListByDate(String date);
}
