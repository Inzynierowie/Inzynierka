package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/price")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/create")
    public void create(@RequestBody Price price) {
        priceService.create(price);
    }

    @PutMapping("/update")
    public void update(@RequestBody Price price) {
        priceService.update(price);
    }

    @GetMapping("/select/{id}")
    public Optional<Price> selectPriceById(@PathVariable Long id) {
        return priceService.selectPriceById(id);
    }

    @GetMapping("/select")
    public List<Price> selectAll() {
        return priceService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteById(@PathVariable Long id) {
        priceService.deleteById(id);
        return id;
    }
}