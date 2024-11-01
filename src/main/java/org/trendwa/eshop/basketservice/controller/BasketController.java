package org.trendwa.eshop.basketservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.service.BasketService;

import java.util.Optional;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{buyerId}")
    public ResponseEntity<CustomerBasket> getBasketByBuyerId(@PathVariable String buyerId) {
        Optional<CustomerBasket> basket = basketService.getBasketByBuyerId(buyerId);
        return basket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CustomerBasket> updateBasket(@RequestBody CustomerBasket basket) {
        CustomerBasket updatedBasket = basketService.updateBasket(basket);
        return ResponseEntity.ok(updatedBasket);
    }

    @DeleteMapping("/{buyerId}")
    public ResponseEntity<Void> deleteBasketByBuyerId(@PathVariable String buyerId) {
        basketService.deleteBasketByBuyerId(buyerId);
        return ResponseEntity.noContent().build();
    }
}