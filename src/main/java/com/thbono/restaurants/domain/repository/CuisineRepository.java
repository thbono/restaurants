package com.thbono.restaurants.domain.repository;

import com.thbono.restaurants.domain.model.Cuisine;
import org.springframework.stereotype.Repository;

@Repository
public class CuisineRepository extends CSVRepository<Cuisine> {

  public CuisineRepository() {
    super("data/cuisines.csv", true);
  }

  @Override
  protected Cuisine buildEntity(String[] row) {
    return new Cuisine(Integer.parseInt(row[0]), row[1]);
  }
}
