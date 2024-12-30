package com.LittleLanka.product_service.controller;

import com.LittleLanka.product_service.dto.request.RequestSaveProductDTO;
import com.LittleLanka.product_service.dto.request.RequestSaveStockDTO;
import com.LittleLanka.product_service.dto.request.RequestUpdatePriceTO;
import com.LittleLanka.product_service.dto.response.*;
import com.LittleLanka.product_service.service.ProductService;
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
    public ResponseEntity<ResponseSaveProductDTO> saveProduct(@RequestBody RequestSaveProductDTO requestSaveProductDTO){
        ResponseSaveProductDTO responseSaveProductDTO = productService.saveProduct(requestSaveProductDTO);
        return new ResponseEntity<>(responseSaveProductDTO, HttpStatus.CREATED);
    }

    @PostMapping("/save-stock")
    public ResponseEntity<ResponseSaveStockDTO> saveStock(@RequestBody RequestSaveStockDTO requestSaveStockDTO){
        ResponseSaveStockDTO responseSaveStockDTO = productService.saveStock(requestSaveStockDTO);
        return new ResponseEntity<>(responseSaveStockDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update-price")
    public ResponseEntity<ResponseUpdatePriceDTO> updatePrice(@RequestBody RequestUpdatePriceTO requestUpdatePriceTO){
        ResponseUpdatePriceDTO responseUpdatePriceDTO = productService.updatePrice(requestUpdatePriceTO);
        return new ResponseEntity<>(responseUpdatePriceDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update-stockQty-by-outletId-and-productId/{outletId}/{productId}/{qty}")
    public ResponseEntity<ResponseUpdateStockDTO> updateStockByOutletIdAndProductId(
            @RequestParam(value = "outletId") Long outletId,
            @RequestParam(value = "productId") Long productId,
            @RequestParam(value = "qty") int qty
    ){
        ResponseUpdateStockDTO responseUpdateStockDTO = productService.updateStockByOutletIdAndProductId(outletId, productId, qty);
        return new ResponseEntity<>(responseUpdateStockDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-price-by-date-and-productId/{date}/{productId}")
    public ResponseEntity<Double> getPriceByDateAndProductId(
            @RequestParam(value = "date") String date,
            @RequestParam(value = "productId") Long productId) {
        Double price = productService.getPriceByDateAndProductId(date, productId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }


    @GetMapping("/get-price-list-by-date/{date}")
    public ResponseEntity<List<ResponsePriceListDTO>> getPriceListByDate(@PathVariable(value = "date") String date) {
        List<ResponsePriceListDTO> priceListDTOS = productService.getPriceListByDate(date);
        return new ResponseEntity<>(priceListDTOS, HttpStatus.OK);
    }

}
