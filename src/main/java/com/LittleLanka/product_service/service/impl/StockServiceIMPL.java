package com.LittleLanka.product_service.service.impl;

import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;
import com.LittleLanka.product_service.entity.Stock;
import com.LittleLanka.product_service.repository.ProductRepository;
import com.LittleLanka.product_service.repository.StockRepository;
import com.LittleLanka.product_service.service.StockService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceIMPL implements StockService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public StockDTO initializeStock(StockDTO stockDTO) {
        if(stockRepository.existsById(stockDTO.getProductId())){
            Boolean isProductAlreadyExists=stockRepository.existsByOutletIdAndProduct(stockDTO.getOutletId(),
                    productRepository.getReferenceById(stockDTO.getProductId()));
            if(!isProductAlreadyExists){
                Stock stock=modelMapper.map(stockDTO, Stock.class);
                Stock stock1=stockRepository.save(stock);
                return modelMapper.map(stock1, StockDTO.class);
            }
        }
        return null;
    }

    @Override
    public StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate) {
        Stock stock=stockRepository.getReferenceById(requestStockUpdate.getStockId());
        double updatedQty=stock.getStockQuantity()-requestStockUpdate.getStockQuantity();
        stock.setStockQuantity(updatedQty);
        Stock stock1=stockRepository.save(stock);
        return modelMapper.map(stock1, StockDTO.class);
    }
}