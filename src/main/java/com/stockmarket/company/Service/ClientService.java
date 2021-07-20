package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.AddIpoStockExchangeDTO;
import com.stockmarket.company.DTO.SectorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {

    private final RestTemplate restTemplate;
    private final String SECTOR_SERVICE = "http://sector-service/api/sector";
    private final String STOCK_PRICE_SERVICE = "http://stock-price-service/api/stock-price";

    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean addIpoStockExchange(AddIpoStockExchangeDTO addIpoStockExchangeDTO){
        HttpEntity<AddIpoStockExchangeDTO> request = new HttpEntity<>(addIpoStockExchangeDTO);
        Boolean result = restTemplate.postForObject(STOCK_PRICE_SERVICE+"/ipo-stock-exchange/add", request, Boolean.class);
        return result!=null && result;
    }

    public SectorDTO getSector(Long sectorId) {
        return restTemplate.
                getForObject(SECTOR_SERVICE+"get/"+sectorId, SectorDTO.class);
    }
}
