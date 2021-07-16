package com.stockmarket.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class AddIpoStockExchangeDTO {
    private Long ipoId;
    private List<Long> stockExchangeIds;
}
