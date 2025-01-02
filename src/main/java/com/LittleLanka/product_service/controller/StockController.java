package com.LittleLanka.product_service.controller;

import com.LittleLanka.product_service.dto.StockDTO;
import com.LittleLanka.product_service.dto.request.RequestStockUpdateDto;
import com.LittleLanka.product_service.service.StockService;
import com.LittleLanka.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @PostMapping("/stock-initialize")
    public ResponseEntity<StandardResponse> initializeStock(@RequestBody StockDTO stockDTO) {
        StockDTO stockDTO1=stockService.initializeStock(stockDTO);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(),
                "Successfully initialized the stock",stockDTO1),
                HttpStatus.CREATED);
    }

    @PutMapping("stock-update-by-id-Qty")
    public ResponseEntity<StandardResponse> updateStockById(@RequestBody RequestStockUpdateDto requestStockUpdate) {
        StockDTO stockDTO=stockService.updateStockByIdQty(requestStockUpdate);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.CREATED.value(),"Successfully updated stock",stockDTO),
                HttpStatus.CREATED);
    }
}
