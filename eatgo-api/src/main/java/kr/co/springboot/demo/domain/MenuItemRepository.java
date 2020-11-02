package kr.co.springboot.demo.domain;

import kr.co.springboot.demo.domain.MenuItem;
import kr.co.springboot.demo.domain.Restaurant;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
