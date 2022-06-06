package com.thbono.restaurants.controller;

import com.thbono.restaurants.domain.model.Restaurant;
import com.thbono.restaurants.domain.service.RestaurantService;
import com.thbono.restaurants.domain.service.search.RestaurantSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  private final RestaurantService service;

  public RestaurantController(RestaurantService service) {
    this.service = service;
  }

  @GetMapping
  public List<Restaurant> findAll() {
    return service.findAll();
  }

  @GetMapping("best")
  public List<Restaurant> findBest(
      @RequestParam("name") Optional<String> name,
      @RequestParam("rating") Optional<Integer> rating,
      @RequestParam("distance") Optional<Integer> distance,
      @RequestParam("price") Optional<Integer> price,
      @RequestParam("cuisine") Optional<String> cuisine) {
    return service.findBest(new RestaurantSearch(name, rating, distance, price, cuisine));
  }
}
