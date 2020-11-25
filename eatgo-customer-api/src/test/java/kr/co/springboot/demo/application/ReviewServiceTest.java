package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.Review;
import kr.co.springboot.demo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("mat-it-da")
                .build();

        reviewService.addReview(10004L,review);

        verify(reviewRepository).save(any());
    }

}