package org.trendwa.eshop.basketservice.service;

import org.trendwa.eshop.basketservice.model.CustomerBasket;

import java.util.Optional;

public interface BasketService {
    Optional<CustomerBasket> getBasketByBuyerId(String buyerId);
    CustomerBasket updateBasket(CustomerBasket basket);
    void deleteBasketByBuyerId(String buyerId);
}