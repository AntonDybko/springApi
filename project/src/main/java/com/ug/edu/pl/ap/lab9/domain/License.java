package com.ug.edu.pl.ap.lab9.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String licenseNumber;
    private String issuingAuthority;
    private String licenseType;
    private String licenseHolderName;
    private String licenseHolderAddress;
    @OneToOne(fetch = FetchType.EAGER)
    public Food food;
}
