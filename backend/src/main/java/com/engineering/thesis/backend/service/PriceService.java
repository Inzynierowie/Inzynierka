package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Price;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    void create(Price price);
    void deleteById(Long id);
    List<Price> selectAll();
    Optional<Price> selectPriceById(Long id);
}