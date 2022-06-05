package com.thbono.restaurants.domain.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CuisineRepositoryTest {

  private final CuisineRepository repository = new CuisineRepository();

  @Test
  void should_find_all() {
    var cuisines = repository.findAll();
    assertEquals(19, cuisines.size());

    var cuisine = cuisines.get(0);
    assertEquals(1, cuisine.id());
    assertEquals("American", cuisine.name());
  }
}
