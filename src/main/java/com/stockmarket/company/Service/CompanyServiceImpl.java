package com.stockmarket.company.Service;

import com.stockmarket.company.DAO.Company;
import com.stockmarket.company.DAO.IPO;
import com.stockmarket.company.DTO.*;
import com.stockmarket.company.Helper.ServiceResponse;
import com.stockmarket.company.Repository.CompanyInfo;
import com.stockmarket.company.Repository.CompanyRepository;
import com.stockmarket.company.Repository.IPOInfo;
import com.stockmarket.company.Repository.IPORepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final IPORepository ipoRepository;
    private final ModelMapper modelMapper;
    private final SectorClientService sectorClientService;
    private final StockPriceClientService stockPriceClientService;

    public CompanyServiceImpl(CompanyRepository companyRepository, IPORepository ipoRepository, ModelMapper modelMapper, SectorClientService sectorClientService, StockPriceClientService stockPriceClientService) {
        this.companyRepository = companyRepository;
        this.ipoRepository = ipoRepository;
        this.modelMapper = modelMapper;
        this.sectorClientService = sectorClientService;
        this.stockPriceClientService = stockPriceClientService;
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
        Company savedCompany = companyRepository.save(modelMapper.map(addCompanyDTO, Company.class));
        if(companyRepository.existsById(savedCompany.getId())){
            return new ServiceResponse(HttpStatus.CREATED, modelMapper.map(savedCompany, CompanyDTO.class));
        }
        return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
    }

    @Override
    public ServiceResponse getCompanyById(Long id){
       CompanyInfo companyInfo = companyRepository.getCompanyById(id);
        if(companyInfo!=null){
            return new ServiceResponse(HttpStatus.OK, companyInfo);
        }
        return new ServiceResponse(HttpStatus.NOT_FOUND,"Company ID does not Exist");
    }

    @Override
    public ServiceResponse getAllCompanies(){
        return new ServiceResponse(HttpStatus.OK, companyRepository.getAll());
    }

    @Override
    public ServiceResponse getCompaniesBySector(Long sectorId) {
        return new ServiceResponse(HttpStatus.OK, companyRepository.findAllBySectorId(sectorId));
    }

    @Override
    public ServiceResponse addIpo(AddIpoDTO addIpoDTO) {
        if(ipoRepository.existsByCompanyId(addIpoDTO.getCompanyId())){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "IPO for given Company already Exists");
        }
        IPO savedIpo = ipoRepository.save(modelMapper.map(addIpoDTO, IPO.class));
        if(ipoRepository.existsById(savedIpo.getId())){
            try {
                boolean result = stockPriceClientService.addIpoStockExchange(new AddIpoStockExchangeDTO(savedIpo.getId(), addIpoDTO.getStockExchangeIds()));
                if (!result) {
                    throw new Exception("Error Dude");
                }
            }catch (Exception e){
                ipoRepository.delete(savedIpo);
                return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
            }
            updateCompanyStatus(addIpoDTO.getCompanyId(), true);
            return new ServiceResponse(HttpStatus.CREATED, modelMapper.map(savedIpo, IpoDTO.class));
        }
        return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
    }

    void updateCompanyStatus(Long companyId, Boolean status){
        companyRepository.updateCompanyStatus(companyId, status);
    }

    @Override
    public ServiceResponse getIPOById(Long id){
        IPOInfo ipoInfo = ipoRepository.getIPOById(id);
        if(ipoInfo!=null){
            return new ServiceResponse(HttpStatus.OK, ipoInfo);
        }
        return new ServiceResponse(HttpStatus.NOT_FOUND,"IPO ID does not Exist");
    }

    @Override
    public ServiceResponse getAllIPOs(){
        return new ServiceResponse(HttpStatus.OK, ipoRepository.getAll());
    }
}
