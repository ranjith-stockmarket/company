package com.stockmarket.company.Service;

import com.stockmarket.company.DTO.AddCompanyDTO;
import com.stockmarket.company.DTO.AddIpoDTO;
import com.stockmarket.company.Helper.ServiceResponse;

public interface CompanyService {
    ServiceResponse addCompany(AddCompanyDTO addCompanyDTO);

    ServiceResponse getCompanyById(Long id);

    ServiceResponse getAllCompanies();

    ServiceResponse getCompaniesBySector(Long sectorId);

    ServiceResponse addIpo(AddIpoDTO addIpoDTO);

    ServiceResponse getIPOById(Long id);

    ServiceResponse getAllIPOs();
}
