package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Override
    public Price create(Price price) throws ResourceNotFoundException {
        Optional<Price> priceOptional = priceRepository.findById(price.getId());
        if (priceOptional.isPresent()) {
            throw new ResourceNotFoundException("Price with Id " + price.getId() + " already exists");
        }
        return priceRepository.save(price);
    }

    @Override
    public Price update(Price price) throws ResourceNotFoundException {
        Optional<Price> priceOptional = priceRepository.findById(price.getId());
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Price with Id " + price.getId() + " doesn't  exists");
        }
        return priceRepository.save(price);
    }

    @Override
    public Long deleteById(Long id) throws ResourceNotFoundException {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Price with Id " + id + " doesn't  exists");
        }
        priceRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Price> selectAll() {
        return priceRepository.findAll();
    }

    @Override
    public Optional<Price> selectPriceById(Long id) throws ResourceNotFoundException {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Price with Id " + id + " doesn't  exists");
        }
        return priceRepository.findById(id);
    }
}