package com.thbono.restaurants.domain.service.search;

import com.thbono.restaurants.domain.model.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantPredicatesTest {

  @Test
  void should_compare_by_name() {
    var restaurant = new Restaurant("Wagon Chow", 5, 10, 10, 1);

    assertTrue(RestaurantPredicates.byName("Wagon Chow").test(restaurant));
    assertTrue(RestaurantPredicates.byName("wagon chow").test(restaurant));
    assertTrue(RestaurantPredicates.byName("WAGON CHOW").test(restaurant));
    assertTrue(RestaurantPredicates.byName("Wag").test(restaurant));
    assertTrue(RestaurantPredicates.byName("Cho").test(restaurant));

    assertFalse(RestaurantPredicates.byName("Wagon Chows").test(restaurant));
  }

  @Test
  void should_compare_by_rating() {
    var restaurant = new Restaurant("A", 3, 10, 10, 1);

    assertTrue(RestaurantPredicates.byRating(1).test(restaurant));
    assertTrue(RestaurantPredicates.byRating(2).test(restaurant));
    assertTrue(RestaurantPredicates.byRating(3).test(restaurant));

    assertFalse(RestaurantPredicates.byRating(4).test(restaurant));
    assertFalse(RestaurantPredicates.byRating(5).test(restaurant));
  }

  @Test
  void should_compare_by_distance() {
    var restaurant = new Restaurant("A", 3, 10, 10, 1);

    assertTrue(RestaurantPredicates.byDistance(10).test(restaurant));
    assertTrue(RestaurantPredicates.byDistance(11).test(restaurant));
    assertTrue(RestaurantPredicates.byDistance(20).test(restaurant));

    assertFalse(RestaurantPredicates.byDistance(9).test(restaurant));
    assertFalse(RestaurantPredicates.byDistance(5).test(restaurant));
  }

  @Test
  void should_compare_by_price() {
    var restaurant = new Restaurant("A", 5, 10, 10, 1);

    assertTrue(RestaurantPredicates.byPrice(10).test(restaurant));
    assertTrue(RestaurantPredicates.byPrice(11).test(restaurant));
    assertTrue(RestaurantPredicates.byPrice(20).test(restaurant));

    assertFalse(RestaurantPredicates.byPrice(9).test(restaurant));
    assertFalse(RestaurantPredicates.byPrice(5).test(restaurant));
  }

  @Test
  void should_compare_by_cuisine_ids() {
    var restaurant = new Restaurant("A", 5, 10, 10, 1);

    assertTrue(RestaurantPredicates.byCuisineIds(Collections.singleton(1)).test(restaurant));
    assertTrue(RestaurantPredicates.byCuisineIds(Stream.of(1, 2)
            .collect(Collectors.toCollection(HashSet::new))).test(restaurant));

    assertFalse(RestaurantPredicates.byCuisineIds(Collections.singleton(2)).test(restaurant));
    assertFalse(RestaurantPredicates.byCuisineIds(Stream.of(2, 3)
            .collect(Collectors.toCollection(HashSet::new))).test(restaurant));
  }
}
