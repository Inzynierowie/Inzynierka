package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

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
    public Optional<Price> selectPriceById(Long id) {
        return priceRepository.findById(id);
    }
}