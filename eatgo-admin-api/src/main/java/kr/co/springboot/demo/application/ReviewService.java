package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.Review;
import kr.co.springboot.demo.domain.ReviewReposiotey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewReposiotey reviewReposiotey;

    @Autowired
    public ReviewService(ReviewReposiotey reviewReposiotey){
        this.reviewReposiotey = reviewReposiotey;
    }

    public List<Review> getReviews() {
        return reviewReposiotey.findAll();
    }
}
