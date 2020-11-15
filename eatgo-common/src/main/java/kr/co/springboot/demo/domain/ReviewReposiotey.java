package kr.co.springboot.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  ReviewReposiotey extends CrudRepository<Review, Long> {

    List<Review> findAll();

    List<Review> findAllByRestaurantId(Long restaurantId);

    Review save(Review review);
}
