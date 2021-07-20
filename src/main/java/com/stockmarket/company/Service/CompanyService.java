package com.stockmarket.company.Service;

import com.stockmarket.company.DAO.Company;
import com.stockmarket.company.DAO.IPO;
import com.stockmarket.company.DTO.*;
import com.stockmarket.company.Helper.ServiceResponse;
import com.stockmarket.company.Repository.CompanyRepository;
import com.stockmarket.company.Repository.IPORepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService{

    private final CompanyRepository companyRepository;
    private final IPORepository ipoRepository;
    private final ModelMapper modelMapper;
    private final ClientService clientService;

    public CompanyService(CompanyRepository companyRepository, IPORepository ipoRepository, ModelMapper modelMapper, ClientService clientService) {
        this.companyRepository = companyRepository;
        this.ipoRepository = ipoRepository;
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    public ServiceResponse addCompany(AddCompanyDTO addCompanyDTO){
        if(companyRepository.existsByName(addCompanyDTO.getName())){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Company already Exists");
        }
        SectorDTO sectorDTO = clientService.getSector(addCompanyDTO.getSectorId());
        if(sectorDTO==null){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Sector Id does not Exist");
        }
        Company savedCompany = companyRepository.save(modelMapper.map(addCompanyDTO, Company.class));
        if(companyRepository.existsById(savedCompany.getId())){
            return new ServiceResponse(HttpStatus.CREATED, modelMapper.map(savedCompany, CompanyDTO.class));
        }
        return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
    }

    public ServiceResponse getCompanyById(Long id){
       Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return new ServiceResponse(HttpStatus.OK, modelMapper.map(company.get(), CompanyDTO.class));
        }
        return new ServiceResponse(HttpStatus.NOT_FOUND,"Company ID does not Exist");
    }

    public ServiceResponse getAllCompanies(){
        return new ServiceResponse(HttpStatus.OK,
                companyRepository
                        .findAll()
                        .stream()
                        .map( company -> modelMapper.map(company, CompanyDTO.class))
                        .collect(Collectors.toList())
        );
    }

    public ServiceResponse getCompaniesBySector(Long sectorId) {
        return new ServiceResponse(HttpStatus.OK,
                new CompaniesDTO(
                        companyRepository
                                .findBySectorId(sectorId)
                                .stream()
                                .map(company -> modelMapper.map(company, CompanyDTO.class))
                                .collect(Collectors.toList())
                ));
    }

    public ServiceResponse addIpo(AddIpoDTO addIpoDTO) {
        if(ipoRepository.existsByCompanyId(addIpoDTO.getCompanyId())){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "IPO for given Company already Exists");
        }
        IPO savedIpo = ipoRepository.save(modelMapper.map(addIpoDTO, IPO.class));
        if(ipoRepository.existsById(savedIpo.getId())){
            try {
                if(!clientService.addIpoStockExchange(new AddIpoStockExchangeDTO(savedIpo.getId(), addIpoDTO.getStockExchangeIds()))) {
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

    public ServiceResponse getIPOById(Long id){
        Optional<IPO> ipo = ipoRepository.findById(id);
        if(ipo.isPresent()){
            return new ServiceResponse(HttpStatus.OK, modelMapper.map(ipo, IpoDTO.class));
        }
        return new ServiceResponse(HttpStatus.NOT_FOUND,"IPO ID does not Exist");
    }

    public ServiceResponse getAllIPOs(){
        return new ServiceResponse(HttpStatus.OK,
                ipoRepository
                        .findAll()
                        .stream()
                        .map( ipo -> modelMapper.map(ipo, IpoDTO.class))
                        .collect(Collectors.toList())
        );
    }
}
