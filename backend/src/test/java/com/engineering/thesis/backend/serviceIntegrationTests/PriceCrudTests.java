package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import com.engineering.thesis.backend.testObj.Prices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PriceCrudTests {

    @Mock(lenient = true)
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Test
    void shouldSavedPriceSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(priceRepository.findById(Prices.priceNull.getId())).willReturn(Optional.of(Prices.priceNull));
        given(priceRepository.save(Prices.priceNull)).willReturn(Prices.priceNull);
        assertThat(priceRepository.save(Prices.priceNull)).isNotNull();
        verify(priceRepository).save(any(Price.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePriceWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(priceRepository.findById(Prices.price1.getId())).willReturn(Optional.of(Prices.price1));
        assertThrows(ResourceNotFoundException.class, () -> priceServiceImpl.create(Prices.price1));
        verify(priceRepository, never()).save(any(Price.class));
    }

    @Test
    void shouldThrowExceptionWhileUpdatePrice() {
        given(priceRepository.save(Prices.price1)).willReturn(Prices.price1);
        assertThrows(ResourceNotFoundException.class, () -> priceServiceImpl.update(Prices.price1));
        verify(priceRepository, never()).save(any(Price.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Price> prices = new ArrayList<>();
        prices.add(Prices.price1);
        prices.add(Prices.price2);
        prices.add(Prices.price3);
        given(priceRepository.findAll()).willReturn(prices);
        List<Price> expected = priceServiceImpl.selectAll();
        assertEquals(expected, prices);
    }

    @Test
    void shouldFindPriceById() throws ResourceNotFoundException {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(priceRepository.findById(id)).willReturn(Optional.of(Prices.price1));
        final Optional<Price> expected = priceServiceImpl.selectPriceById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(ResourceNotFoundException.class, () -> priceServiceImpl.deleteById(Prices.price1.getId()));
        verify(priceRepository, never()).deleteById(Prices.price1.getId());
    }
}