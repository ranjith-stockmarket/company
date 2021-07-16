package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.AddIpoStockExchangeDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Headers("Content-Type: application/json")
@FeignClient(name = "stock-price", url = "http://localhost:8084/api/stock-price")
public interface StockPriceClientService {

//    @GetMapping("/get/{id}")
//    StockExchangeDTO getStockExchangeById(@PathVariable("id") Long id);

    @PostMapping("/ipo-stock-exchange/add")
    Boolean addIpoStockExchange(@RequestBody AddIpoStockExchangeDTO addIpoStockExchangeDTO);

}
