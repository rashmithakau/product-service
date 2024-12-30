package com.LittleLanka.product_service.controller;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.StockDTO;
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

    @PostMapping("/stock-initialize")
    public ResponseEntity<StandardResponse> initializeStock(@RequestBody StockDTO stockDTO) {
        StockDTO stockDTO1=productService.initializeStock(stockDTO);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(), "Successfully initialized the stock",stockDTO1),
                HttpStatus.CREATED);
    }

    @PutMapping("/price-update")
    public ResponseEntity<StandardResponse> updatePrice(@RequestBody RequestPriceUpdateDto requestPriceUpdateDto) {
        PriceUpdateDTO priceUpdateDTO=productService.updatePrice(requestPriceUpdateDto);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(), "Successfully updated price",priceUpdateDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("stock-update-by-id-Qty")
    public ResponseEntity<StandardResponse> updateStockById(@RequestBody RequestStockUpdateDto requestStockUpdate) {
        StockDTO stockDTO=productService.updateStockByIdQty(requestStockUpdate);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(),"Successfully updated stock",stockDTO),
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
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(),"Successfully loaded the price list", priceListDTOS),
                HttpStatus.CREATED);
    }
}
