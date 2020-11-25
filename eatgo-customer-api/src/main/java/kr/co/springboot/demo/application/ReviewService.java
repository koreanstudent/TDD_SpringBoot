package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.Review;
import kr.co.springboot.demo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewReposiotory;

    @Autowired
    public ReviewService(ReviewRepository reviewReposiotory){
        this.reviewReposiotory = reviewReposiotory;
    }

    public Review addReview(Long restaurantId, Review review){
         review.setRestaurantId(restaurantId);
         reviewReposiotory.save(review);

         return review;
    }
}
