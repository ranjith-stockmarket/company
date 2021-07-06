package com.stockmarket.company.DAO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter @Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ceo")
    private String ceo;

    @Column(name = "board_of_directors")
    private String boardOfDirectors;

    @Column(name = "listed")
    private Boolean listed;

    @Column(name = "sector_id")
    private Long sectorId;

    @Column(name = "brief")
    private String brief;
}
