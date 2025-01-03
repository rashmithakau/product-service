package com.LittleLanka.product_service.service;

import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.request.*;


public interface ProductService {
    ProductDTO saveProduct(RequestSaveProductDto requestSaveProductDTO);
}
