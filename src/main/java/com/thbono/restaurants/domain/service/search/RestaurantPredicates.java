package com.thbono.restaurants.domain.service.search;

import com.thbono.restaurants.domain.model.Restaurant;

import java.util.function.Predicate;

public class RestaurantPredicates {

  public static Predicate<Restaurant> byName(String name) {
    return restaurant -> restaurant.name().toUpperCase().contains(name.toUpperCase());
  }

  public static Predicate<Restaurant> byRating(Integer rating) {
    return restaurant -> restaurant.rating() >= rating;
  }

  public static Predicate<Restaurant> byDistance(Integer distance) {
    return restaurant -> restaurant.distance() <= distance;
  }

  public static Predicate<Restaurant> byPrice(Integer price) {
    return restaurant -> restaurant.price() <= price;
  }

  public static Predicate<Restaurant> byCuisineName(String cuisineName) {
    return restaurant ->
        restaurant.cuisine() != null
            && restaurant.cuisine().name().toUpperCase().contains(cuisineName.toUpperCase());
  }
}
