package com.example.reviewms.service;

import com.example.reviewms.pojos.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> getReviews(Long companyId);

    public boolean createReview(Long companyId, Review review);

    public Review getReview(Long reviewId);

    public boolean updateReview(Long reviewId, Review updatedReview);

    public boolean deleteReview(Long reviewId);

}
