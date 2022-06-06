package com.thbono.restaurants.domain.service.search;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public final class CriteriaHelper {

  private CriteriaHelper() {}

  public static <T> void addPredicate(AtomicReference<Predicate<T>> criteria, Predicate<T> predicate) {
    criteria.set(criteria.get().and(predicate));
  }
}
