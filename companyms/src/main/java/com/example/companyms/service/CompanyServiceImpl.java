package com.example.companyms.service;

import com.example.companyms.pojos.Company;
import com.example.companyms.repo.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        // Company company = companyRepository.getById(id);
        if (company != null) {
            return company;
        } else {
            return new Company();
        }
    }

    public boolean updateCompany(Long id, Company updatedCompany) {

        Company company = getCompany(id);

        if (company.getId() != null) {
            company.setDescription(updatedCompany.getDescription());
            // company.setJobs(updatedCompany.getJobs());
            company.setName(updatedCompany.getName());

            companyRepository.save(company);
            return true;

        }

        return false;
    }

    public boolean deleteCompany(Long id) {
        /*Company company = getCompany(id);

        if (company.getId() != null) {
            List<Job> jobs = company.getJobs();

            for (Job job : jobs) {
                job.setCompany(null);
            }

            companyRepository.deleteById(id);
            return true;
        }

        return false;*/
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
