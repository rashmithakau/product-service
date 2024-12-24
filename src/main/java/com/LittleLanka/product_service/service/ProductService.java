package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;

public interface ProductService {
    ProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO);
    Double getPriceByDateAndProductId(String date, Long id);
}
