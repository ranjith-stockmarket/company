package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.AddCompanyDTO;
import com.stockmarket.company.DTO.CompanyDTO;
import com.stockmarket.company.Helper.ServiceResponse;

import java.util.List;

public interface CompanyService {
    ServiceResponse addCompany(AddCompanyDTO addCompanyDTO);

    ServiceResponse getCompanyById(Long id);

    ServiceResponse getAllCompanies();

    ServiceResponse getCompaniesBySector(Long sectorId);
}
