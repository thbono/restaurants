package com.thbono.restaurants.domain.service;

import com.thbono.restaurants.domain.model.Cuisine;
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
  private final CuisineService cuisineService;

  public RestaurantService(RestaurantRepository repository, CuisineService cuisineService) {
    this.repository = repository;
    this.cuisineService = cuisineService;
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

    search.cuisine().ifPresent(cuisine -> {
      var cuisineIds = cuisineService.findByName(cuisine).stream().map(Cuisine::id).collect(Collectors.toSet());
      addPredicate(criteria, RestaurantPredicates.byCuisineIds(cuisineIds));
    });

    var matches = repository.findByCriteria(criteria.get());
    return matches.stream().sorted().limit(BEST_SEARCH_LIMIT).collect(Collectors.toList());
  }

}
