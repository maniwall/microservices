package com.example.companyms.service;

import com.example.companyms.pojos.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    void addCompany(Company company);

    Company getCompany(Long id);

    boolean updateCompany(Long id, Company updatedCompany);

    boolean deleteCompany(Long id);
}
