package org.trendwa.eshop.basketservice.exception;

/**
 * Exception thrown when a customer basket is not found for a given buyer ID.
 */
public class CustomerBasketNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerBasketNotFoundException with the specified buyer ID.
     *
     * @param buyerId the ID of the buyer whose basket was not found
     */
    public CustomerBasketNotFoundException(String buyerId) {
        super("Customer basket not found for buyer ID: " + buyerId);
    }
}