package org.trendwa.eshop.basketservice.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.trendwa.eshop.basketservice.exception.CustomerBasketNotFoundException;
import org.trendwa.eshop.basketservice.model.BasketItem;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.service.BasketService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasketController.class)
class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @Test
    void testGetBasketByBuyerId() throws Exception {

        CustomerBasket basket = prepareBasket();
        when(basketService.getBasketByBuyerId("123")).thenReturn(basket);

        mockMvc.perform(get("/api/basket/{buyerId}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyerId").value(basket.getBuyerId()));
    }

    @Test
    void testGetBasketByBuyerId_NotFound() throws Exception {
        when(basketService.getBasketByBuyerId("123")).thenThrow(new CustomerBasketNotFoundException("123"));

        mockMvc.perform(get("/api/basket/{buyerId}", "123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBasket() throws Exception {
        CustomerBasket basket = prepareBasket();
        when(basketService.updateBasket(any(CustomerBasket.class))).thenReturn(basket);

        mockMvc.perform(post("/api/basket")
                        .contentType("application/json")
                        .content(TEST_BASKET_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyerId").value(basket.getBuyerId()));

    }

    @Test
    void testDeleteBasketByBuyerId() throws Exception {
        String buyerId = "123";

        doNothing().when(basketService).deleteBasketByBuyerId(buyerId);

        mockMvc.perform(delete("/api/basket/{buyerId}", buyerId))
                .andExpect(status().isNoContent());

        Mockito.verify(basketService, Mockito.times(1)).deleteBasketByBuyerId(buyerId);
    }

    private CustomerBasket prepareBasket() {
        CustomerBasket basket = new CustomerBasket();
        List<BasketItem> items = new ArrayList<>();
        BasketItem item1 = new BasketItem();
        item1.setProductId(1);
        item1.setQuantity(2);
        item1.setUnitPrice(10.0);
        item1.setOldUnitPrice(12.0);
        item1.setProductName("Product 1");
        item1.setPictureUrl("http://cdn.net/product1.jpg");
        items.add(item1);

        BasketItem item2 = new BasketItem();
        item2.setProductId(2);
        item2.setQuantity(1);
        item2.setUnitPrice(20.0);
        item2.setOldUnitPrice(25.0);
        item2.setProductName("Product 2");
        item2.setPictureUrl("http://cdn.net/product2.jpg");
        items.add(item2);

        basket.setBuyerId("123");
        basket.setItems(items);

        return basket;
    }

    private static final String TEST_BASKET_JSON = """
            {
                "buyerId": "123",
                "items": [
                    {
                        "productId": 1,
                        "quantity": 2,
                        "unitPrice": 10.0,
                        "oldUnitPrice": 12.0,
                        "productName": "Product 1",
                        "pictureUrl": "http://cdn.net/product1.jpg"
                    },
                    {
                        "productId": 2,
                        "quantity": 1,
                        "unitPrice": 20.0,
                        "oldUnitPrice": 25.0,
                        "productName": "Product 2",
                        "pictureUrl": "http://cdn.net/product2.jpg"
                    }
                ]
            }""";

}