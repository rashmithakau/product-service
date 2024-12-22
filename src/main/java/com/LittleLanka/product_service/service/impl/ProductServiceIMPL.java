package com.LittleLanka.product_service.service.impl;

import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.entity.Product;
import com.LittleLanka.product_service.repository.ProductRepository;
import com.LittleLanka.product_service.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO) {
        Product product = modelMapper.map(requestSaveProductDTO, Product.class);
        productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }
}
