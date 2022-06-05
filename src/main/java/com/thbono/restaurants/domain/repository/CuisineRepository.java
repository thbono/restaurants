package com.thbono.restaurants.domain.repository;

import com.thbono.restaurants.domain.model.Cuisine;
import org.springframework.stereotype.Repository;

@Repository
public class CuisineRepository extends CSVRepository<Cuisine> {

  private static final String DATA_FILE = "data/cuisines.csv";

  @Override
  protected String getDataFile() {
    return DATA_FILE;
  }

  @Override
  protected Cuisine buildEntity(String[] line) {
    return new Cuisine(Integer.parseInt(line[0]), line[1]);
  }
}
