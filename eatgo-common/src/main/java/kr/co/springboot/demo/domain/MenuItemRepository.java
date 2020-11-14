package kr.co.springboot.demo.domain;

import kr.co.springboot.demo.domain.MenuItem;
import kr.co.springboot.demo.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
    void deleteById(Long id);
}
