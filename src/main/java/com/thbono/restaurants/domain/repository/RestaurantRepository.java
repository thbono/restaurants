package com.thbono.restaurants.domain.repository;

import com.thbono.restaurants.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository extends CSVRepository<Restaurant> {

  private static final String DATA_FILE = "data/restaurants.csv";

  @Override
  protected String getDataFile() {
    return DATA_FILE;
  }

  @Override
  protected Restaurant buildEntity(String[] line) {
    return new Restaurant(
        line[0],
        Integer.parseInt(line[1]),
        Integer.parseInt(line[2]),
        Integer.parseInt(line[3]),
        Integer.parseInt(line[4]));
  }
}
