package com.thbono.restaurants.domain.repository;

import com.thbono.restaurants.domain.model.Cuisine;
import com.thbono.restaurants.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RestaurantRepository extends CSVRepository<Restaurant> {

  private final Map<Integer, Cuisine> cuisineCache;

  public RestaurantRepository(CuisineRepository cuisineRepository) {
    super("data/restaurants.csv", true);
    this.cuisineCache =
        cuisineRepository.findAll().stream()
            .collect(Collectors.toMap(Cuisine::id, cuisine -> cuisine));
  }

  @Override
  protected Restaurant buildEntity(String[] row) {
    return new Restaurant(
        row[0],
        Integer.parseInt(row[1]),
        Integer.parseInt(row[2]),
        Integer.parseInt(row[3]),
        cuisineCache.get(Integer.valueOf(row[4])));
  }
}
