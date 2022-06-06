package com.thbono.restaurants.domain.repository;

import com.thbono.restaurants.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository extends CSVRepository<Restaurant> {

  public RestaurantRepository() {
    super("data/restaurants.csv", true);
  }

  @Override
  protected Restaurant buildEntity(String[] row) {
    return new Restaurant(
        row[0],
        Integer.parseInt(row[1]),
        Integer.parseInt(row[2]),
        Integer.parseInt(row[3]),
        Integer.parseInt(row[4]));
  }
}
