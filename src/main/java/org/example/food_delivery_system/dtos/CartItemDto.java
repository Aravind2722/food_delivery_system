package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private long foodItemId;
    private String foodItemName;
    private int quantity;
    private double price;
    private double sum;
}
