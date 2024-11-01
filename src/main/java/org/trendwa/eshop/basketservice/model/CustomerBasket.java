package org.trendwa.eshop.basketservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@RedisHash("customer_basket")
@NoArgsConstructor
public class CustomerBasket {

    @Id
    private String buyerId;
    private List<BasketItem> items;

    public CustomerBasket(String buyerId) {
        this.buyerId = buyerId;
    }
}
