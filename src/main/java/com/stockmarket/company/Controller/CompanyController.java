package com.stockmarket.company.Controller;

import com.stockmarket.company.DTO.AddCompanyDTO;
import com.stockmarket.company.DTO.CompanyDTO;
import com.stockmarket.company.Service.SectorClientService;
import com.stockmarket.company.Service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final SectorClientService sectorClientService;

    private final CompanyService companyService;

    public CompanyController(SectorClientService sectorClientService, CompanyService companyService) {
        this.sectorClientService = sectorClientService;
        this.companyService = companyService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@Valid @RequestBody AddCompanyDTO addCompanyDTO){
        return companyService.addCompany(addCompanyDTO).getResponse();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCompanyById(@PathVariable("id") Long id){
        return companyService.getCompanyById(id).getResponse();
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllCompanies(){
        return companyService.getAllCompanies().getResponse();
    }

    @GetMapping("/getBySector/{sectorId}")
    public ResponseEntity<Object> getCompaniesBySector(@PathVariable("sectorId") Long sectorId){
        return companyService.getCompaniesBySector(sectorId).getResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(" , ")));
    }

}
