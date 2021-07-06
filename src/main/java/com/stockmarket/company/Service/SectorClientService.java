package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.SectorDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Headers("Content-Type: application/json")
@FeignClient(name = "sector", url = "http://localhost:8081/api/sector")
public interface SectorClientService {
    @GetMapping("/get/{id}")
    SectorDTO getSectorById(@PathVariable("id") Long id);
}
