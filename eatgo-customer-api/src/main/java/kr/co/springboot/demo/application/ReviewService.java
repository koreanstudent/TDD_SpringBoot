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

    public Review addReview(Long restaurantId, String name,Integer score, String description){

        Review review = Review.builder()
                .restaurantId(restaurantId)
                .name(name)
                .score(score)
                .description(description)
                .build();
         review.setRestaurantId(restaurantId);
         reviewReposiotory.save(review);

         return review;
    }
}
