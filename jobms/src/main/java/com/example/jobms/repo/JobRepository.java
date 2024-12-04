package com.example.jobms.repo;

import com.example.jobms.pojos.Job;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {

//    @Query
//    public void findJob();


}
