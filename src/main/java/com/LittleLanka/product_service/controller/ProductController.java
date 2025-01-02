package com.LittleLanka.product_service.controller;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestDateAndPriceListDTO;
import com.LittleLanka.product_service.dto.request.RequestPriceUpdateDto;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDto;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;
import com.LittleLanka.product_service.dto.response.ResponsePriceListDTO;
import com.LittleLanka.product_service.service.ProductService;
import com.LittleLanka.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save-product")
    public ResponseEntity<StandardResponse> saveProduct(@RequestBody RequestSaveProductDto requestSaveProductDTO) {
        ProductDTO productDTO = productService.saveProduct(requestSaveProductDTO);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(), "Successfully saved the product",productDTO),
                HttpStatus.CREATED);
    }



    @PutMapping("/price-update")
    public ResponseEntity<StandardResponse> updatePrice(@RequestBody RequestPriceUpdateDto requestPriceUpdateDto) {
        PriceUpdateDTO priceUpdateDTO=productService.updatePrice(requestPriceUpdateDto);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(), "Successfully updated price",priceUpdateDTO),
                HttpStatus.CREATED);
    }



    @GetMapping("/get-price-by-date-and-productId/{date}/{id}")
    public ResponseEntity<StandardResponse> getPriceByDateAndProductId(
            @PathVariable("date") String date,
            @PathVariable("id") Long id)
    {
        Double price = productService.getPriceByDateAndProductId(date, id);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Successfully retrieved price", price),
                HttpStatus.OK);
    }


    @GetMapping("/get-price-list-by-date/{date}")
    public ResponseEntity<StandardResponse> getPriceListByDate(@PathVariable(value = "date") String date) {
        List<ResponsePriceListDTO> priceListDTOS = productService.getPriceListByDate(date);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(),"Successfully loaded the price list", priceListDTOS),
                HttpStatus.OK);
    }

    @PostMapping("/get-price-list-by-date-and-productId-list")
    public ResponseEntity<StandardResponse> getPriceListByDateAndProductIdList(
            @RequestBody RequestDateAndPriceListDTO requestDateAndPriceListDTO) {
        List<ResponsePriceListDTO> priceListDTOS = productService.getPriceListByDateAndProductIdList(requestDateAndPriceListDTO);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(), "Successfully got the price list", priceListDTOS), HttpStatus.OK);
    }
}
