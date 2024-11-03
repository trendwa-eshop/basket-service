package org.trendwa.eshop.basketservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.trendwa.eshop.basketservice.exception.CustomerBasketNotFoundException;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.repository.BasketRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RedisBasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    @InjectMocks
    private RedisBasketService redisBasketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasketByBuyerId_BasketExists() throws CustomerBasketNotFoundException {
        String buyerId = "buyer1";
        CustomerBasket basket = new CustomerBasket();
        when(basketRepository.findById(buyerId)).thenReturn(Optional.of(basket));

        CustomerBasket result = redisBasketService.getBasketByBuyerId(buyerId);

        assertEquals(basket, result);
        verify(basketRepository, times(1)).findById(buyerId);
    }

    @Test
    void testGetBasketByBuyerId_BasketNotFound() {
        String buyerId = "buyer1";
        when(basketRepository.findById(buyerId)).thenReturn(Optional.empty());

        assertThrows(CustomerBasketNotFoundException.class, () -> redisBasketService.getBasketByBuyerId(buyerId));
        verify(basketRepository, times(1)).findById(buyerId);
    }

    @Test
    void testUpdateBasket() {
        CustomerBasket basket = new CustomerBasket();
        when(basketRepository.save(basket)).thenReturn(basket);

        CustomerBasket result = redisBasketService.updateBasket(basket);

        assertEquals(basket, result);
        verify(basketRepository, times(1)).save(basket);
    }

    @Test
    void testDeleteBasketByBuyerId_BasketExists() {
        String buyerId = "buyer1";
        when(basketRepository.existsById(buyerId)).thenReturn(true);

        redisBasketService.deleteBasketByBuyerId(buyerId);

        verify(basketRepository, times(1)).deleteById(buyerId);
    }

    @Test
    void testDeleteBasketByBuyerId_BasketNotFound() {
        String buyerId = "buyer1";
        when(basketRepository.existsById(buyerId)).thenReturn(false);

        assertThrows(CustomerBasketNotFoundException.class, () -> redisBasketService.deleteBasketByBuyerId(buyerId));
        verify(basketRepository, times(1)).existsById(buyerId);
    }
}