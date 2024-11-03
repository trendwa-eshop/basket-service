package org.trendwa.eshop.basketservice.service;

import org.trendwa.eshop.basketservice.exception.CustomerBasketNotFoundException;
import org.trendwa.eshop.basketservice.model.CustomerBasket;


/**
 * Service for managing customer baskets.
 */
public interface BasketService {

    /**
     * Retrieves a customer's basket by their buyer ID.
     *
     * @param buyerId the ID of the buyer
     * @return the CustomerBasket associated with the given buyer ID
     * @throws CustomerBasketNotFoundException if no basket is found for the given buyer ID
     */
    CustomerBasket getBasketByBuyerId(String buyerId) throws CustomerBasketNotFoundException;

    /**
     * Updates the given customer basket.
     *
     * @param basket the CustomerBasket to update
     * @return the updated CustomerBasket
     */
    CustomerBasket updateBasket(CustomerBasket basket);

    /**
     * Deletes a customer's basket by their buyer ID.
     *
     * @param buyerId the ID of the buyer
     */
    void deleteBasketByBuyerId(String buyerId);
}