package com.stockmarket.company.Controller;

import com.stockmarket.company.DTO.AddCompanyDTO;
import com.stockmarket.company.DTO.AddIpoDTO;
import com.stockmarket.company.Service.CompanyService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@Valid @RequestBody AddCompanyDTO addCompanyDTO){
        return companyService.addCompany(addCompanyDTO).getResponse();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable("id") Long id){
        return companyService.getCompanyById(id).getResponse();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCompanies(){
        return companyService.getAllCompanies().getResponse();
    }

    @GetMapping("/getBySector/{sectorId}")
    public ResponseEntity<?> getCompaniesBySector(@PathVariable("sectorId") Long sectorId){
        return companyService.getCompaniesBySector(sectorId).getResponse();
    }

    @PostMapping("/ipo/add")
    public ResponseEntity<?> addIPO(@Valid @RequestBody AddIpoDTO addIpoDTO){
        return companyService.addIpo(addIpoDTO).getResponse();
    }

    @GetMapping("/ipo/get/{id}")
    public ResponseEntity<?> getIPOById(@PathVariable("id") Long id){
        return companyService.getIPOById(id).getResponse();
    }

    @GetMapping("/ipo/getAll")
    public ResponseEntity<?> getAllIPOs(){
        return companyService.getAllIPOs().getResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)//.map( fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(" , ")));
    }

}
