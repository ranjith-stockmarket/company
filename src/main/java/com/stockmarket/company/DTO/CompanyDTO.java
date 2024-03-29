package com.stockmarket.company.DTO;

import com.stockmarket.company.DAO.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String name;
    private String ceo;
    private String boardOfDirectors;
    private Boolean listed;
    private Long sectorId;
    private String brief;
}
