package com.thbono.restaurants.domain.service.search;

import com.thbono.restaurants.domain.model.Cuisine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CuisinePredicatesTest {
  @Test
  void should_compare_by_name() {
    var cuisine = new Cuisine(1, "Japanese");

    assertTrue(CuisinePredicates.byName("Japanese").test(cuisine));
    assertTrue(CuisinePredicates.byName("japanese").test(cuisine));
    assertTrue(CuisinePredicates.byName("JAPANESE").test(cuisine));
    assertTrue(CuisinePredicates.byName("Jap").test(cuisine));
    assertTrue(CuisinePredicates.byName("pan").test(cuisine));
    assertTrue(CuisinePredicates.byName("ese").test(cuisine));

    assertFalse(CuisinePredicates.byName("Japaneses").test(cuisine));
  }
}
