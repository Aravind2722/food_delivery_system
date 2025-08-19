package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.food_delivery_system.models.DietaryPreference;

@Getter
@Setter
public class AddFoodItemRequestDto {
    private long restaurantId;
    private long adminId;
    private String foodName;
    private String description;
    private DietaryPreference dietaryPreference;
    private double price;
    private String image;
}
