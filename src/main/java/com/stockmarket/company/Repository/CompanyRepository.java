package com.stockmarket.company.Repository;

import com.stockmarket.company.DAO.Company;
import com.stockmarket.company.DTO.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);

    @Query("select new com.stockmarket.company.DTO.CompanyDTO(c) from Company c where c.id=?1")
    CompanyDTO getById(Long id);

    @Query("select new com.stockmarket.company.DTO.CompanyDTO(c) from Company c order by c.id")
    List<CompanyDTO> getAll();

    @Query("select new com.stockmarket.company.DTO.CompanyDTO(c) from Company c where c.sectorId=?1")
    List<CompanyDTO> findAllBySectorId(Long sectorId);
}
