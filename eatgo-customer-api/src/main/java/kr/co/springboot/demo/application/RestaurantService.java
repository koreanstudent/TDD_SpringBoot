package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository , ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants(String region, Long categoryId) {
        List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region,categoryId);

        return restaurants;
    }
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> review = reviewRepository.findAllByRestaurantId(id);

        return restaurant;
    }


    public Restaurant addRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, String name, String address) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        restaurant.updateInformation(name, address);

        return restaurant;
    }
}
