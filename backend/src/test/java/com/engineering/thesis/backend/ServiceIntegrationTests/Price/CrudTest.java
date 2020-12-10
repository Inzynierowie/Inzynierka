package com.engineering.thesis.backend.ServiceIntegrationTests.Price;

import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.PriceRepository;
import com.engineering.thesis.backend.service.PriceService;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrudTest {

    @Mock(lenient = true)
    private PriceRepository priceService;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Test
    void shouldSavedPriceSuccessFully() {
        final Price price = new Price(null, "Testing",1000l);
        given(priceServiceImpl.selectPriceById(price.getId()))
                .willReturn(Optional.empty());
        given(priceService.save(price)).willAnswer(invocation -> invocation.getArgument(0));
        Price savedUser = priceService.save(price);
        assertThat(savedUser).isNotNull();
        verify(priceService).save(any(Price.class));
    }

    /*@Test
    void shouldThrowErrorWhenSaveUserWithExistingEmail() {
        final Price price = new Price(1L, "Testing",1000l);

        given(priceService.findByEmail(price.getEmail())).willReturn(Optional.of(price));

        assertThrows(UserRegistrationException.class,() -> {
            priceServiceImpl.createUser(price);
        });

        verify(priceService, never()).save(any(Price.class));
    }

    @Test
    void updateUser() {
        final Price price = new Price(1L, "ten@mail.com","teten","teten");

        given(priceService.save(price)).willReturn(price);

        final Price expected = priceServiceImpl.updateUser(price);

        assertThat(expected).isNotNull();

        verify(priceService).save(any(Price.class));
    }

    @Test
    void shouldReturnFindAll() {
        List<Price> datas = new ArrayList();
        datas.add(new Price(1L, "ten@mail.com","teten","teten"));
        datas.add(new Price(2L, "ten@mail.com","teten","teten"));
        datas.add(new Price(3L, "ten@mail.com","teten","teten"));

        given(priceService.findAll()).willReturn(datas);

        List<Price> expected = priceServiceImpl.findAllUsers();

        assertEquals(expected, datas);
    }

    @Test
    void findUserById(){
        final Long id = 1L;
        final Price price = new Price(1L, "ten@mail.com","teten","teten");

        given(priceService.findById(id)).willReturn(Optional.of(price));

        final Optional<Price> expected  = priceServiceImpl.findUserById(id);

        assertThat(expected).isNotNull();

    }

    @Test
    void shouldBeDelete() {
        final Long priceId=1L;

        priceServiceImpl.deleteUserById(priceId);
        priceServiceImpl.deleteUserById(priceId);

        verify(priceService, times(2)).deleteById(priceId);
    }*/


}
