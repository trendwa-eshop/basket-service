package org.trendwa.eshop.basketservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BasketItem {
    private String id;
    private int productId;
    private String productName;
    private double unitPrice;
    private double oldUnitPrice;
    private int quantity;
    private String pictureUrl;
}
