package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddFoodItemIntoCartResponseDto {
    private List<CartItemDto> cartItemDtos;
    private double total;
}
