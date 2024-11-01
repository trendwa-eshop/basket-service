package org.trendwa.eshop.basketservice;

import org.springframework.boot.SpringApplication;

public class TestBasketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(BasketServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
