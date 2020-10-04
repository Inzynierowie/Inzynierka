package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @PostMapping("/create")
    public void create(@RequestBody Price price){
        priceService.create(price);
    }

    @GetMapping("/select/{id}")
    public Price selectPriceById(@PathVariable Long id) {
        return priceService.selectPriceById(id);
    }

    @GetMapping("/select")
    public List<Price> selectAll(){
        return priceService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        priceService.deleteById(id);
    }
}
