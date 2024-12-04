package com.example.companyms.repo;

import com.example.companyms.pojos.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
