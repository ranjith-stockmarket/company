package com.stockmarket.company.Service;

import com.stockmarket.company.DAO.Company;
import com.stockmarket.company.DTO.AddCompanyDTO;
import com.stockmarket.company.DTO.CompaniesDTO;
import com.stockmarket.company.DTO.CompanyDTO;
import com.stockmarket.company.DTO.SectorDTO;
import com.stockmarket.company.Helper.ServiceResponse;
import com.stockmarket.company.Repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final SectorClientService sectorClientService;

    public CompanyServiceImpl(CompanyRepository companyRepository, SectorClientService sectorClientService) {
        this.companyRepository = companyRepository;
        this.sectorClientService = sectorClientService;
    }

    @Override
    public ServiceResponse addCompany(AddCompanyDTO addCompanyDTO){
        if(companyRepository.existsByName(addCompanyDTO.getName())){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Company already Exists");
        }
        SectorDTO sectorDTO = sectorClientService.getSectorById(addCompanyDTO.getSectorId());
        if(sectorDTO==null){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Sector Id does not Exist");
        }
        Company savedCompany = companyRepository.save(addCompanyDTO.getCompany());
        if(companyRepository.existsById(savedCompany.getId())){
            return new ServiceResponse(HttpStatus.CREATED, new CompanyDTO(savedCompany));
        }
        return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
    }

    @Override
    public ServiceResponse getCompanyById(Long id){
        CompanyDTO companyDTO = companyRepository.getById(id);
        if(companyDTO!=null){
            companyDTO.setSectorName(sectorClientService.getSectorById(companyDTO.getSectorId()).getName());
            return new ServiceResponse(HttpStatus.OK, companyDTO);
        }
        return new ServiceResponse(HttpStatus.NOT_FOUND,"Company ID does not Exist");
    }

    @Override
    public ServiceResponse getAllCompanies(){
        return new ServiceResponse(HttpStatus.OK, companyRepository.getAll());
    }

    @Override
    public ServiceResponse getCompaniesBySector(Long sectorId) {
        return new ServiceResponse(HttpStatus.OK, new CompaniesDTO(companyRepository.findAllBySectorId(sectorId)));
    }
}
