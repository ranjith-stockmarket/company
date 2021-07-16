package com.stockmarket.company.Repository;

public interface CompanyInfo {
    Long getId();

    String getBoardOfDirectors();

    String getBrief();

    String getCeo();

    Boolean getListed();

    String getName();

    Long getSectorId();
}
