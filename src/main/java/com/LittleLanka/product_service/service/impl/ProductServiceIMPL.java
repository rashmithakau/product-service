package com.LittleLanka.product_service.service.impl;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.queryInterfaces.PriceListInterface;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;
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
    public ProductDTO saveProduct(RequestSaveProductDTO requestSaveProductDTO) {
        Product product = modelMapper.map(requestSaveProductDTO, Product.class);
        productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
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

}
