package com.example.jobms.service;

import com.example.jobms.dto.JobDTO;
import com.example.jobms.pojos.Job;

import java.util.List;

public interface JobService {

    List<JobDTO> findAllJobs();

    void createJob(Job jobInputValue);

    JobDTO getJobById(Long id);

    Boolean deleteJob(Long id);

    Job updateJob(Long id, Job updatedJob);
}
