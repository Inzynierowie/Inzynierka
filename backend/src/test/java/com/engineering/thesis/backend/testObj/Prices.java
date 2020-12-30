package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.Price;

public class Prices {

    public static Price priceNull = Price.builder()
            .id(null)
            .treatment("Biopsy")
            .price(1000L)
            .build();

    public static Price price1 = Price.builder()
            .id(1L)
            .treatment("Biopsy")
            .price(1000L)
            .build();

    public static Price price2 = Price.builder()
            .id(2L)
            .treatment("Biopsy")
            .price(1000L)
            .build();

    public static Price price3 = Price.builder()
            .id(3L)
            .treatment("Biopsy")
            .price(1000L)
            .build();
}
