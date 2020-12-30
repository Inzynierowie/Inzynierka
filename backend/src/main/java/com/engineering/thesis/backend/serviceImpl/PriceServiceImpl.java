package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.exception.DeleteObjException;
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
    public Price create(Price price) {
        Optional<Price> priceOptional = priceRepository.findById(price.getId());
        if (priceOptional.isPresent()) {
            throw new CreateObjException("Price with Id " + price.getId() + " already exists");
        }
        return priceRepository.save(price);
    }

    @Override
    public Price update(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Long deleteById(Long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isEmpty()) {
            throw new DeleteObjException("Price with Id " + id + " don't exists");
        }
        priceRepository.deleteById(id);
        return id;
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