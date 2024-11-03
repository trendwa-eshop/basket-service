package org.trendwa.eshop.basketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trendwa.eshop.basketservice.exception.CustomerBasketNotFoundException;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.repository.BasketRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisBasketService implements BasketService {

    private final BasketRepository basketRepository;

    @Override
    public CustomerBasket getBasketByBuyerId(String buyerId) throws CustomerBasketNotFoundException {
        Optional<CustomerBasket> basket = basketRepository.findById(buyerId);
        if (basket.isEmpty()) {
            throw new CustomerBasketNotFoundException(buyerId);
        }
        return basket.get();
    }

    @Override
    public CustomerBasket updateBasket(CustomerBasket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public void deleteBasketByBuyerId(String buyerId) {
        if(basketRepository.existsById(buyerId)) basketRepository.deleteById(buyerId);
        throw new CustomerBasketNotFoundException(buyerId);
    }
}