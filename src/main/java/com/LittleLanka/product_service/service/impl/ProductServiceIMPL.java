package com.LittleLanka.product_service.service.impl;


import com.LittleLanka.product_service.dto.queryInterfaces.PriceListInterface;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveStockDTO;
import com.LittleLanka.product_service.dto.request.RequestUpdatePriceTO;
import com.LittleLanka.product_service.dto.response.*;
import com.LittleLanka.product_service.entity.PriceUpdate;
import com.LittleLanka.product_service.entity.Product;
import com.LittleLanka.product_service.entity.Stock;
import com.LittleLanka.product_service.repository.PriceUpdateRepository;
import com.LittleLanka.product_service.repository.ProductRepository;
import com.LittleLanka.product_service.repository.StockRepository;
import com.LittleLanka.product_service.service.ProductService;
import org.modelmapper.ModelMapper;
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
    private StockRepository stockRepository;
    @Autowired
    private ModelMapper modelMapper;

    private ResponseSaveProductDTO mapToResponseSaveProductDTO(Product product, PriceUpdate priceUpdate) {
        ResponseSaveProductDTO responseSaveProductDTO = new ResponseSaveProductDTO();
        // Map Product entity fields
        responseSaveProductDTO.setProductId(product.getProductId());
        responseSaveProductDTO.setProductName(product.getProductName());
        responseSaveProductDTO.setProductCategory(product.getProductCategory());
        responseSaveProductDTO.setProductStatus(product.isProductStatus()); // Map productStatus
        responseSaveProductDTO.setMeasuringType(product.getMeasuringType());
        // Map PriceUpdate entity fields
        responseSaveProductDTO.setPriceUpdateId(priceUpdate.getPriceUpdateId());
        responseSaveProductDTO.setUpdateDate(priceUpdate.getUpdateDate());
        responseSaveProductDTO.setPrice(priceUpdate.getPrice());
        return responseSaveProductDTO;
    }

    private Date makeDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = null;
        try {
            dateObj = formatter.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateObj;
    }





    @Override
    public ResponseSaveProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO) {
        // Map the request DTO to Product entity
        Product product = modelMapper.map(requestSaveProductDTO, Product.class);
        // Save the Product entity (this is creating a new product)
        Product savedProduct = productRepository.save(product);
        // Create a new PriceUpdate entity with the product price and the provided update date
        PriceUpdate priceUpdate = new PriceUpdate();
        priceUpdate.setProduct(savedProduct);
        priceUpdate.setPrice(requestSaveProductDTO.getPrice()); // Set the price from DTO
        priceUpdate.setUpdateDate(requestSaveProductDTO.getUpdateDate()); // Set the provided updateDate from DTO
        // Save the new PriceUpdate entity
        PriceUpdate savedPriceUpdate = priceUpdateRepository.save(priceUpdate);
        // Map the saved product and price update to the response DTO
        return mapToResponseSaveProductDTO(savedProduct, savedPriceUpdate);
    }



    @Override
    public ResponseSaveStockDTO saveStock(RequestSaveStockDTO requestSaveStockDTO) {
        // Find the product by productId
        Product product = productRepository.findById(requestSaveStockDTO.getProductId()).get();
        // Map request DTO to Stock entity
        Stock stock = new Stock();
        stock.setOutletId(requestSaveStockDTO.getOutletId());
        stock.setStockQuantity(requestSaveStockDTO.getStockQuantity());
        stock.setProduct(product);
        // Save the Stock entity
        Stock savedStock = stockRepository.save(stock);
        // Map the saved Stock entity to ResponseSaveStockDTO
        ResponseSaveStockDTO responseSaveStockDTO = new ResponseSaveStockDTO();
        responseSaveStockDTO.setStockId(savedStock.getStockId());
        responseSaveStockDTO.setOutletId(savedStock.getOutletId());
        responseSaveStockDTO.setStockQuantity(savedStock.getStockQuantity());
        responseSaveStockDTO.setProductId(product.getProductId());
        responseSaveStockDTO.setProductName(product.getProductName());
        return responseSaveStockDTO;
    }



    @Override
    public ResponseUpdatePriceDTO updatePrice(RequestUpdatePriceTO requestUpdatePriceTO) {
        // Find the product by productId to ensure the product exists
        Product product = productRepository.findById(requestUpdatePriceTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Create a new PriceUpdate entity with the new price and update date
        PriceUpdate priceUpdate = new PriceUpdate();
        priceUpdate.setProduct(product);
        priceUpdate.setPrice(requestUpdatePriceTO.getPrice()); // Set the new price
        priceUpdate.setUpdateDate(requestUpdatePriceTO.getUpdateDate()); // Set the new update date
        // Save the new PriceUpdate record
        PriceUpdate savedPriceUpdate = priceUpdateRepository.save(priceUpdate);
        // Map the saved PriceUpdate to ResponseUpdatePriceDTO
        ResponseUpdatePriceDTO responseUpdatePriceDTO = new ResponseUpdatePriceDTO();
        responseUpdatePriceDTO.setPriceUpdateId(savedPriceUpdate.getPriceUpdateId());
        responseUpdatePriceDTO.setUpdateDate(savedPriceUpdate.getUpdateDate());
        responseUpdatePriceDTO.setPrice(savedPriceUpdate.getPrice());
        responseUpdatePriceDTO.setProductId(product.getProductId());
        responseUpdatePriceDTO.setProductName(product.getProductName());
        return responseUpdatePriceDTO;
    }



    @Override
    public ResponseUpdateStockDTO updateStockByOutletIdAndProductId(Long outletId, Long productId, int qty) {
        // Find the Stock entity by outletId and productId
        Stock stock = stockRepository.findByOutletIdAndProductProductId(outletId, productId);
        // Update the stockQuantity with the new quantity (qty)
        stock.setStockQuantity(qty);
        // Save the updated Stock entity
        Stock updatedStock = stockRepository.save(stock);
        // Map the updated Stock entity to ResponseUpdateStockDTO using ModelMapper
        ResponseUpdateStockDTO responseUpdateStockDTO = modelMapper.map(updatedStock, ResponseUpdateStockDTO.class);
        return responseUpdateStockDTO;
    }


    @Override
    public Double getPriceByDateAndProductId(String date, Long productId) {
        Date dateObj = makeDate(date);
        Double price = priceUpdateRepository.findPriceUpdateByPriceUpdateDateAndProductId(dateObj, productId);
        return price;
    }


    @Override
    public List<ResponsePriceListDTO> getPriceListByDate(String date) {
        Date dateObj = makeDate(date);
        List<PriceListInterface> priceListDTOS = priceUpdateRepository.findProductIdAndPriceByDateEquals(dateObj);
        List<ResponsePriceListDTO> responsePriceListDTOS = new ArrayList<>();
        for (PriceListInterface priceListDTO : priceListDTOS) {
            ResponsePriceListDTO responsePriceListDTO = new ResponsePriceListDTO(
                    priceListDTO.getProductId(),
                    priceListDTO.getPrice()
            );
            responsePriceListDTOS.add(responsePriceListDTO);
        }
        return responsePriceListDTOS;
    }


}
