package com.stockmarket.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class CompaniesDTO {
    List<CompanyDTO> companies;
}
