package com.LittleLanka.product_service.service.impl;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.entity.PriceUpdate;
import com.LittleLanka.product_service.entity.Product;
import com.LittleLanka.product_service.repository.PriceUpdateRepository;
import com.LittleLanka.product_service.repository.ProductRepository;
import com.LittleLanka.product_service.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceUpdateRepository priceUpdateRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO) {
        Product product = modelMapper.map(requestSaveProductDTO, Product.class);
        productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public Double getPriceByDateAndProductId(String date, Long id) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = null;
        try {
            dateObj = formatter.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        Double price = priceUpdateRepository.findPriceUpdateByPriceUpdateDateAndProductId(dateObj, id);
        return price;
    }
}
