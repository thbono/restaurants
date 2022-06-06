package com.thbono.restaurants.domain.service;

import com.thbono.restaurants.domain.model.Restaurant;
import com.thbono.restaurants.domain.repository.RestaurantRepository;
import com.thbono.restaurants.domain.service.search.RestaurantPredicates;
import com.thbono.restaurants.domain.service.search.RestaurantSearch;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.thbono.restaurants.domain.service.search.CriteriaHelper.addPredicate;

@Service
public class RestaurantService {

  private static final Integer BEST_SEARCH_LIMIT = 5;

  private final RestaurantRepository repository;

  public RestaurantService(RestaurantRepository repository) {
    this.repository = repository;
  }

  @Cacheable("restaurants.findAll")
  public List<Restaurant> findAll() {
    return repository.findAll();
  }

  @Cacheable("restaurants.findBest")
  public List<Restaurant> findBest(final RestaurantSearch search) {
    search.validate();
    final AtomicReference<Predicate<Restaurant>> criteria = new AtomicReference<>(restaurant -> true);

    search.name().ifPresent(name -> addPredicate(criteria, RestaurantPredicates.byName(name)));
    search.rating().ifPresent(rating -> addPredicate(criteria, RestaurantPredicates.byRating(rating)));
    search.distance().ifPresent(distance -> addPredicate(criteria, RestaurantPredicates.byDistance(distance)));
    search.price().ifPresent(price -> addPredicate(criteria, RestaurantPredicates.byPrice(price)));
    search.cuisine().ifPresent(cuisine -> addPredicate(criteria, RestaurantPredicates.byCuisineName(cuisine)));

    var matches = repository.findByCriteria(criteria.get());
    return matches.stream().sorted().limit(BEST_SEARCH_LIMIT).collect(Collectors.toList());
  }

}
