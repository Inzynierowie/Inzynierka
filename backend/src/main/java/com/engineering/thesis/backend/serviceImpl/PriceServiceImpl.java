package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.PriceCreateException;
import com.engineering.thesis.backend.model.Patient;
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
    public Price create(Price price) {
        Optional<Price> priceOptiona = priceRepository.findById(price.getId());
        if(priceOptiona.isPresent()) {
            throw new PriceCreateException("User with email "+ price.getId()+" already exists");
        }
        return priceRepository.save(price);
    }

    @Override
    public Price update(Price price) {
        return priceRepository.save(price);
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