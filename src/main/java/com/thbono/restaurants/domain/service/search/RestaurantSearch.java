package com.thbono.restaurants.domain.service.search;

import com.thbono.restaurants.domain.service.exception.ValidationException;

import java.time.temporal.ValueRange;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public record RestaurantSearch(
        Optional<String> name,
        Optional<Integer> rating,
        Optional<Integer> distance,
        Optional<Integer> price,
        Optional<String> cuisine
) {

    public void validate() {
        var valid = new AtomicBoolean(true);

        rating.ifPresent(value -> valid.set(valid.get() && ValueRange.of(1, 5).isValidIntValue(value)));
        distance.ifPresent(value -> valid.set(valid.get() && ValueRange.of(1, 10).isValidIntValue(value)));
        price.ifPresent(value -> valid.set(valid.get() && ValueRange.of(10, 50).isValidIntValue(value)));

        if (!valid.get()) {
            throw new ValidationException("Invalid search parameters");
        }
    }

}
