package com.LittleLanka.product_service.controller;

import com.LittleLanka.product_service.dto.PriceUpdateDTO;
import com.LittleLanka.product_service.dto.ProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save-product")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody RequestSaveProductDTO requestSaveProductDTO) {
        ProductDTO productDTO = productService.saveProduct(requestSaveProductDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-price-by-date-and-productId/{date}/{id}")
    public ResponseEntity<Double> getPriceByDateAndProductId(
            @RequestParam(value = "date") String date,
            @RequestParam(value = "id") Long id) {
        Double price = productService.getPriceByDateAndProductId(date, id);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }


}
