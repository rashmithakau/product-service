package com.LittleLanka.product_service.service.impl;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.queryInterfaces.PriceListInterface;
import com.LittleLanka.product_service.dto.request.*;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;
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
    private ModelMapper modelMapper;
    @Autowired
    private StockRepository stockRepository;

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
    public ProductDTO saveProduct(RequestSaveProductDto requestSaveProductDTO) {
        Product product = modelMapper.map(requestSaveProductDTO, Product.class);
        if(!productRepository.existsByProductCatagoryAndProductName(requestSaveProductDTO.getProductCatagory()
                , requestSaveProductDTO.getProductName()))
        {
            productRepository.save(product);
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            return productDTO;
        }
        return null;

    }

    @Override
    public Double getPriceByDateAndProductId(String date, Long id) {
        Date dateObj = makeDate(date);
        Double price = priceUpdateRepository.findPriceUpdateByPriceUpdateDateAndProductId(dateObj, id);
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

    @Override
    public StockDTO initializeStock(StockDTO stockDTO) {
        if(productRepository.existsById(stockDTO.getProductId())){
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
    public PriceUpdateDTO updatePrice(RequestPriceUpdateDto requestPriceUpdateDto) {
        PriceUpdate priceUpdate=modelMapper.map(requestPriceUpdateDto, PriceUpdate.class);
        PriceUpdate priceUpdateOut=priceUpdateRepository.save(priceUpdate);
        return modelMapper.map(priceUpdateOut, PriceUpdateDTO.class);
    }

    @Override
    public StockDTO updateStockByIdQty(RequestStockUpdateDto requestStockUpdate) {
        Stock stock=stockRepository.getReferenceById(requestStockUpdate.getStockId());
        double updatedQty=stock.getStockQuantity()-requestStockUpdate.getStockQuantity();
        stock.setStockQuantity(updatedQty);
        Stock stock1=stockRepository.save(stock);
        return modelMapper.map(stock1, StockDTO.class);
    }

    @Override
    public List<ResponsePriceListDTO> getPriceListByDateAndProductIdList(RequestDateAndPriceListDTO requestDateAndPriceListDTO) {
        String date = requestDateAndPriceListDTO.getDate();
        List<ResponsePriceListDTO> priceListByDate = getPriceListByDate(date); // Fetch all prices for the date
        // Filter the prices based on the product IDs provided in the request
        List<Long> productIds = requestDateAndPriceListDTO.getProductIds();
        List<ResponsePriceListDTO> filteredPriceList = new ArrayList<>();
        for (ResponsePriceListDTO priceListDTO : priceListByDate) {
            if (productIds.contains(priceListDTO.getProductId())) {
                filteredPriceList.add(priceListDTO);
            }
        }
        return filteredPriceList;
    }


}
