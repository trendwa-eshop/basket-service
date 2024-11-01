package org.trendwa.eshop.basketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.repository.BasketRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Override
    public Optional<CustomerBasket> getBasketByBuyerId(String buyerId) {
        return basketRepository.findById(buyerId);
    }

    @Override
    public CustomerBasket updateBasket(CustomerBasket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public void deleteBasketByBuyerId(String buyerId) {
        basketRepository.deleteById(buyerId);
    }
}