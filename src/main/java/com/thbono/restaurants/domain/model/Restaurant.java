package com.thbono.restaurants.domain.model;

public record Restaurant(
        String name,
        int rating,
        int distance,
        int price,
        int cuisine_id
) {}
