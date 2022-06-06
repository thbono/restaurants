package com.thbono.restaurants.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

  @Test
  void should_compare_by_distance() {
    var restaurant1 = new Restaurant("A", 5, 10, 10, 1);
    var restaurant2 = new Restaurant("A", 5, 8, 10, 1);
    assertEquals(1, restaurant1.compareTo(restaurant2));
  }

  @Test
  void should_compare_by_rating() {
    var restaurant1 = new Restaurant("A", 4, 10, 10, 1);
    var restaurant2 = new Restaurant("A", 5, 10, 10, 1);
    assertEquals(1, restaurant1.compareTo(restaurant2));
  }

  @Test
  void should_compare_by_price() {
    var restaurant1 = new Restaurant("A", 5, 10, 10, 1);
    var restaurant2 = new Restaurant("A", 5, 10, 8, 1);
    assertEquals(1, restaurant1.compareTo(restaurant2));
  }

  @Test
  void should_compare_by_name() {
    var restaurant1 = new Restaurant("B", 5, 10, 10, 1);
    var restaurant2 = new Restaurant("A", 5, 10, 10, 1);
    assertEquals(1, restaurant1.compareTo(restaurant2));
  }

  @Test
  void should_compare_by_natural_order() {
    var restaurant1 = new Restaurant("A", 5, 10, 10, 1);
    var restaurant2 = new Restaurant("A", 5, 10, 10, 1);
    assertEquals(0, restaurant1.compareTo(restaurant2));
  }
}
