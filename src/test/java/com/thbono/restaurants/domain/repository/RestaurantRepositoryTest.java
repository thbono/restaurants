package com.thbono.restaurants.domain.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantRepositoryTest {

  private final RestaurantRepository repository = new RestaurantRepository();

  @Test
  void should_find_all() {
    var restaurants = repository.findAll();
    assertEquals(200, restaurants.size());

    var cuisine = restaurants.get(0);
    assertEquals("Deliciousgenix", cuisine.name());
  }

  @Test
  void should_find_by_criteria() {
    var restaurants = repository.findByCriteria(restaurant -> restaurant.name().equals("Chowish"));
    assertEquals(1, restaurants.size());

    var cuisine = restaurants.get(0);
    assertEquals("Chowish", cuisine.name());
  }
}
