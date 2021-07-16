package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.StockExchangeDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Headers("Content-Type: application/json")
@FeignClient(name = "stock-exchange", url = "http://localhost:8083/api/stock-exchange")
public interface StockExchangeClientService {

    @GetMapping("/get/{id}")
    StockExchangeDTO getStockExchangeById(@PathVariable("id") Long id);

}
