package com.thbono.restaurants.controller;

import com.thbono.restaurants.domain.model.Restaurant;
import com.thbono.restaurants.domain.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
