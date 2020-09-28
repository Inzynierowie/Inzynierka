package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public void create(Price price) {
        priceRepository.save(price);
    }

    @Override
    public void deleteById(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public List<Price> selectAll() {
        return priceRepository.findAll();
    }

    @Override
    public Price selectPriceById(Long id) {
        Price price = priceRepository.findById(id).get();
        return price;
    }
}