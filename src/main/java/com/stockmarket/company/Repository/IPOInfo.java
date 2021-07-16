package com.stockmarket.company.Repository;

import java.sql.Timestamp;

public interface IPOInfo {
    Long getId();

    Long getCompanyId();

    Timestamp getOpenDateTime();

    Float getPricePerShare();

    String getRemarks();

    Integer getTotalShares();
}
