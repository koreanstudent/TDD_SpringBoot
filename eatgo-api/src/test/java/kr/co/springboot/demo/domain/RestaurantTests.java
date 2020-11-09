package kr.co.springboot.demo.domain;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RestaurantTests {

    @Test
    public void creation() {
//        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();



        Assert.assertThat(restaurant.getId(), Is.is(1004L));
    }
}