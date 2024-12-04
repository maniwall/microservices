package com.example.jobms.controller;

import com.example.jobms.dto.JobDTO;
import com.example.jobms.pojos.Job;
import com.example.jobms.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job jobInputValue) {
        jobService.createJob(jobInputValue);
        return new ResponseEntity<>("job created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {

        JobDTO currentJob = jobService.getJobById(id);

        if (!currentJob.equals(null)) {
            return new ResponseEntity<>(currentJob, HttpStatus.OK);
        }
        return new ResponseEntity<>(currentJob, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {

        if (jobService.deleteJob(id)) {
            return new ResponseEntity<>("Deleted Job Successfully!!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Not able to find Job for Deletion", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {

        Job currentJob = jobService.updateJob(id, updatedJob);

        if (!currentJob.equals(null)) {
            return new ResponseEntity<Job>(currentJob, HttpStatus.OK);
        }

        return new ResponseEntity<Job>(currentJob, HttpStatus.NOT_FOUND);
    }
}
