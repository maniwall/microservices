package com.example.jobms.clients;

import com.example.jobms.pojos.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEWMS")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getReview(@RequestParam("companyId") Long companyId);
}
