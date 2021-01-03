package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Price;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    Price create(Price price) throws ResourceNotFoundException;
    Price update(Price price) throws ResourceNotFoundException;
    Long deleteById(Long id) throws ResourceNotFoundException;
    List<Price> selectAll();
    Optional<Price> selectPriceById(Long id) throws ResourceNotFoundException;
}