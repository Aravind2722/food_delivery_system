package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.food_delivery_system.models.FoodItem;

@Getter
@Setter
public class AddFoodItemIntoCartRequestDto {
//    private long customerId;
    private long foodItemId;
    private int quantity;
}
