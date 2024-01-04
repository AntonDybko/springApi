package com.ug.edu.pl.ap.lab6.domain;

import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class License {
    private long id;
    private String licenseNumber;
    private String issuingAuthority;
    private String licenseType;
    private String licenseHolderName;
    private String licenseHolderAddress;
    public License(long id, String licenseNumber, String issuingAuthority, String licenseType, String licenseHolderName, String licenseHolderAddress) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.issuingAuthority = issuingAuthority;
        this.licenseType = licenseType;
        this.licenseHolderName = licenseHolderName;
        this.licenseHolderAddress = licenseHolderAddress;
    }
    public License() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
    @OneToOne
    public String getLicenseHolderName() {
        return licenseHolderName;
    }

    public void setLicenseHolderName(String licenseHolderName) {
        this.licenseHolderName = licenseHolderName;
    }

    public String getLicenseHolderAddress() {
        return licenseHolderAddress;
    }

    public void setLicenseHolderAddress(String licenseHolderAddress) {
        this.licenseHolderAddress = licenseHolderAddress;
    }
}
