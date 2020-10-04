package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Price;

import java.util.List;

public interface PriceService {
    void create(Price price);
    void deleteById(Long id);
    List<Price> selectAll();
    Price selectPriceById(Long id);
}
