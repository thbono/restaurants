package com.thbono.restaurants.domain.service;

import com.thbono.restaurants.domain.model.Restaurant;
import com.thbono.restaurants.domain.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

  private final RestaurantRepository repository;

  public RestaurantService(RestaurantRepository repository) {
    this.repository = repository;
  }

  @Cacheable("restaurants")
  public List<Restaurant> findAll() {
    return repository.findAll();
  }
}
