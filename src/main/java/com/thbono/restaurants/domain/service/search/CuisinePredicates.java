package com.thbono.restaurants.domain.service.search;

import com.thbono.restaurants.domain.model.Cuisine;

import java.util.function.Predicate;

public class CuisinePredicates {

  public static Predicate<Cuisine> byName(String name) {
    return cuisine -> cuisine.name().toUpperCase().contains(name.toUpperCase());
  }
}
