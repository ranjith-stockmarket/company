package com.stockmarket.company.Repository;

import com.stockmarket.company.DAO.IPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface IPORepository extends JpaRepository<IPO, Long> {

    boolean existsByCompanyId(Long companyId);

    IPOInfo getIPOById(Long id);

    @Query("select ipo from IPO ipo order by ipo.id")
    List<IPOInfo> getAll();
    /*
    @Query(value = "create event ?1 on schedule at ?2 do insert ", nativeQuery = true)
    Object chumma(String eventName, Timestamp timestamp);
    */
}
