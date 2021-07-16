package com.stockmarket.company.Repository;

import com.stockmarket.company.DAO.Company;
import com.stockmarket.company.DTO.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("update Company c set c.listed=?2 where c.id=?1")
    void updateCompanyStatus(Long id, Boolean status);

    CompanyInfo getCompanyById(Long id);

    @Query("select c from Company c")
    List<CompanyInfo> getAll();

    @Query("select c from Company c where c.sectorId=?1")
    List<CompanyInfo> findAllBySectorId(Long sectorId);
}
