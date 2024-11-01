package org.trendwa.eshop.basketservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.trendwa.eshop.basketservice.model.CustomerBasket;

@Repository
public interface BasketRepository extends CrudRepository<CustomerBasket, String> {
}