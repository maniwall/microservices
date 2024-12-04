package com.example.reviewms.service;

import com.example.reviewms.pojos.Review;
import com.example.reviewms.repo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        super();
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    public boolean createReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            // review.setDescription("updated Description!!");
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
        // return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    public boolean updateReview(Long reviewId, Review updatedReview) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null) {
            review.setCompanyId(updatedReview.getCompanyId());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }


}
