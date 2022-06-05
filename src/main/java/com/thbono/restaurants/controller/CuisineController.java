package com.thbono.restaurants.controller;

import com.thbono.restaurants.domain.model.Cuisine;
import com.thbono.restaurants.domain.service.CuisineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

  private final CuisineService service;

  public CuisineController(CuisineService service) {
    this.service = service;
  }

  @GetMapping
  public List<Cuisine> findAll() {
    return service.findAll();
  }
}
