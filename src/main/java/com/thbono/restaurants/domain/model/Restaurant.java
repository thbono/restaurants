package com.thbono.restaurants.domain.model;

import java.util.Comparator;

public record Restaurant(
        String name,
        int rating,
        int distance,
        int price,
        int cuisineId
) implements Comparable<Restaurant> {

    @Override
    public int compareTo(Restaurant other) {
        return Comparator.comparingInt(Restaurant::distance)
                .thenComparing(Comparator.comparingInt(Restaurant::rating).reversed())
                .thenComparing(Restaurant::price)
                .thenComparing(Restaurant::name)
                .compare(this, other);
    }

}
