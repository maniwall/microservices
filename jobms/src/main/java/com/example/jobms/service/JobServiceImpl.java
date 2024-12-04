package com.example.jobms.service;

import com.example.jobms.clients.CompanyClient;
import com.example.jobms.clients.ReviewClient;
import com.example.jobms.dto.JobDTO;
import com.example.jobms.pojos.Company;
import com.example.jobms.pojos.Job;
import com.example.jobms.pojos.Review;
import com.example.jobms.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository) {
        // super();
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAllJobs() {

        List<Job> jobs = jobRepository.findAll();
        // RestTemplate restTemplate = new RestTemplate();
        List<JobDTO> jobDTOS = new ArrayList<>();

        for (Job job : jobs) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJob(job);

            // communicating with Company & Review Services using RestTemplate approach
             /* Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/" + job.getCompanyId(), Company.class);
            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http:// :8084/reviews?companyId=" + job.getCompanyId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                    });
            List<Review> reviews = reviewResponse.getBody();*/


            // communicating with Company & Review Services using Open Feign Client approach
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getReview(job.getCompanyId());

            jobDTO.setCompany(company);
            jobDTO.setReviews(reviews);

            jobDTOS.add(jobDTO);
        }

        return jobDTOS;
    }

    @Override
    public void createJob(Job jobInputValue) {
        jobRepository.save(jobInputValue);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {

            // communicating with Company & Review Services using RestTemplate approach
             /* Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/" + job.getCompanyId(), Company.class);
            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http:// :8084/reviews?companyId=" + job.getCompanyId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                    });
            List<Review> reviews = reviewResponse.getBody();*/

            JobDTO jobDTO = new JobDTO();
            Job job = jobOptional.orElseGet(null);

            // communicating with Company & Review Services using Open Feign Client approach
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getReview(job.getCompanyId());

            jobDTO.setCompany(company);
            jobDTO.setReviews(reviews);
            jobDTO.setJob(job);
            return jobDTO;
        }

        return new JobDTO();
    }

    @Override
    public Boolean deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job currentJob = job.get();
            currentJob.setDescription(updatedJob.getDescription());
            currentJob.setLocation(updatedJob.getLocation());
            currentJob.setMaxSalary(updatedJob.getMaxSalary());
            currentJob.setMinSalary(updatedJob.getMinSalary());
            currentJob.setTitle(updatedJob.getTitle());

            jobRepository.save(updatedJob);
            return updatedJob;
        }
        return new Job();
    }
}
