package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceCrudTests {

    @Mock(lenient = true)
    private PriceRepository priceService;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Test
    void shouldSavedPriceSuccessFully() {
        final Price price = new Price(null, "Testing", 1000l);
        given(priceServiceImpl.selectPriceById(price.getId()))
                .willReturn(Optional.empty());
        given(priceService.save(price)).willAnswer(invocation -> invocation.getArgument(0));
        Price savedPrice = priceService.save(price);
        assertThat(savedPrice).isNotNull();
        verify(priceService).save(any(Price.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePriceWithExistingID() {
        final Price price = new Price(1L, "Testing", 1000l);
        given(priceService.findById(price.getId())).willReturn(Optional.of(price));
        assertThrows(CreateObjException.class, () -> {
            priceServiceImpl.create(price);
        });
        verify(priceService, never()).save(any(Price.class));
    }

    @Test
    void shouldUpdatePrice() {
        final Price price = new Price(1L, "Testing", 1000l);
        given(priceService.save(price)).willReturn(price);
        final Price expected = priceServiceImpl.update(price);
        assertThat(expected).isNotNull();
        verify(priceService).save(any(Price.class));
    }

    @Test
    void shouldReturnSelectAll() {
        List<Price> prices = new ArrayList();
        prices.add(new Price(1L, "Testing", 1000l));
        prices.add(new Price(2L, "Testing", 1000l));
        prices.add(new Price(3L, "Testing", 1000l));
        given(priceService.findAll()).willReturn(prices);
        List<Price> expected = priceServiceImpl.selectAll();
        assertEquals(expected, prices);
    }

    @Test
    void shouldFindPriceById() {
        final Long id = 1L;
        final Price price = new Price(1L, "Testing", 1000l);
        given(priceService.findById(id)).willReturn(Optional.of(price));
        final Optional<Price> expected = priceServiceImpl.selectPriceById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldBeDelete() {
        final Long priceId = 1L;
        priceServiceImpl.deleteById(priceId);
        priceServiceImpl.deleteById(priceId);
        verify(priceService, times(2)).deleteById(priceId);
    }
}
