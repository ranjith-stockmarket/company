package com.stockmarket.company.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter @Setter
public class IpoDTO {
    private Long id;
    private Long companyId;
    private Float pricePerShare;
    private Integer totalShares;
    private Timestamp openDateTime;
    private String remarks;
}
