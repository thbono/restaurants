package com.thbono.restaurants.domain.service;

import com.thbono.restaurants.domain.model.Restaurant;
import com.thbono.restaurants.domain.repository.RestaurantRepository;
import com.thbono.restaurants.domain.service.search.RestaurantSearch;
import com.thbono.restaurants.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {

  @Mock
  private RestaurantRepository repository;

  private RestaurantService service;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    service = new RestaurantService(repository);
  }

  @Test
  void should_find_best() {
    var restaurants = TestUtil.loadResourceList("fixtures/restaurants.json", Restaurant.class);
    var bestRestaurants = TestUtil.loadResourceList("fixtures/best-restaurants.json", Restaurant.class);

    when(repository.findByCriteria(any())).thenReturn(restaurants);

    var foundRestaurants = service.findBest(new RestaurantSearch(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

    assertEquals(bestRestaurants, foundRestaurants);
  }
}
