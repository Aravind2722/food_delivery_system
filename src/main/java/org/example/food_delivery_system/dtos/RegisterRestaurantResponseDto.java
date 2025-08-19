package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRestaurantResponseDto {
    private long adminId;
    private long restaurantId;
}
