package com.stockmarket.company.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "ipo")
public class IPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "price_per_share")
    private Float pricePerShare;

    @Column(name = "total_shares")
    private Integer totalShares;

    @Column(name = "open_date_time")
    private Timestamp openDateTime;

    @Column(name = "remarks")
    private String remarks;
}
