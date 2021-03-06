package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        restaurantRepository = new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviwRepository();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);

    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
            .name("Kimchi")
            .build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();

//        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul",1L)).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        

    }


    private void mockReviwRepository() {
        List<Review> review = new ArrayList<>();
        review.add(Review.builder()
                .name("JOKER")
                .score(3)
                .description("mat-it-da")
                .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(review);
    }

    @Test
    public void getRestaurants() {

        String region ="Seoul";
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, 1L);

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));

    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));

        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem =restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(),is("Kimchi"));


    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });
        Restaurant restaurant = Restaurant.builder()
            .name("chul")
            .address("Busan")
            .build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("chul")
                .address("Busan")
                .build();

//        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        
        assertThat(created.getId(), is(1234L));


    }

    @Test
    public void updateRestaurant() {

//        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("chul")
                .address("Busan")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));


        Restaurant updated = restaurantService.updateRestaurant(1004L,"SOOL", "Busan");

        assertThat(updated.getName(), is("SOOL"));
        assertThat(updated.getAddress(), is("Busan"));

    }

}