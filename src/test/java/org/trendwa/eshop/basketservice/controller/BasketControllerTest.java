package org.trendwa.eshop.basketservice.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.trendwa.eshop.basketservice.model.CustomerBasket;
import org.trendwa.eshop.basketservice.service.BasketService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @Test
    void testGetBasketByBuyerId() throws Exception {
        String buyerId = "123";
        CustomerBasket basket = new CustomerBasket();
        when(basketService.getBasketByBuyerId(buyerId)).thenReturn(Optional.of(basket));

        mockMvc.perform(get("/api/basket/{buyerId}", buyerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.buyerId").value(basket.getBuyerId()));
    }

    @Test
    void testGetBasketByBuyerId_NotFound() throws Exception {
        String buyerId = "123";
        when(basketService.getBasketByBuyerId(buyerId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/basket/{buyerId}", buyerId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBasket() throws Exception {
        CustomerBasket basket = new CustomerBasket();
        when(basketService.updateBasket(any(CustomerBasket.class))).thenReturn(basket);

        mockMvc.perform(post("/api/basket")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"items\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.buyerId").value(basket.getBuyerId()));
    }

    @Test
    void testDeleteBasketByBuyerId() throws Exception {
        String buyerId = "123";

        mockMvc.perform(delete("/api/basket/{buyerId}", buyerId))
                .andExpect(status().isNoContent());

        Mockito.verify(basketService, Mockito.times(1)).deleteBasketByBuyerId(buyerId);
    }
}