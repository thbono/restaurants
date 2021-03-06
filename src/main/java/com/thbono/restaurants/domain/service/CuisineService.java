package com.thbono.restaurants.domain.service;

import com.thbono.restaurants.domain.model.Cuisine;
import com.thbono.restaurants.domain.repository.CuisineRepository;
import com.thbono.restaurants.domain.service.search.CuisinePredicates;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineService {

  private final CuisineRepository repository;

  public CuisineService(CuisineRepository repository) {
    this.repository = repository;
  }

  @Cacheable("cuisines.findAll")
  public List<Cuisine> findAll() {
    return repository.findAll();
  }

  @Cacheable("cuisines.findByName")
  public List<Cuisine> findByName(String name) {
    return repository.findByCriteria(CuisinePredicates.byName(name));
  }
}
