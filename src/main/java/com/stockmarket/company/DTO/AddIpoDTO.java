package com.stockmarket.company.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class AddIpoDTO {

    @NotNull(message = "Company Id can't be Null")
    private Long companyId;

    @NotEmpty(message = "Stock Exchange Ids can't be empty")
    private List<Long> stockExchangeIds;

    @NotNull(message = "Price Per Share can't be Null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Float pricePerShare;

    @NotNull(message = "Total shares can't be Null")
    @Min(value = 1, message = "Total Shares must be greater than zero")
    private Integer totalShares;

    @NotNull(message = "Open Date time can't be null")
    @Future(message = "Opening Date Time must be in future")
    private Timestamp openDateTime;

    private String remarks;
}
