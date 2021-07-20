package com.stockmarket.company.DTO;

import com.stockmarket.company.DAO.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class AddCompanyDTO {
    @NotNull(message = "Company Name can't be null")
    @Size(max = 100, message = "Name be less than 100 Characters")
    private String name;
    @Size(max = 255, message = "CEO Name be less than 100 Characters")
    private String ceo;
    private Boolean listed;
    private String boardOfDirectors;
    @NotNull(message = "Sector ID can't be null")
    private Long sectorId;
    private String brief;
}
