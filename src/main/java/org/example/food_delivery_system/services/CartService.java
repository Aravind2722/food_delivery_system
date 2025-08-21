package org.example.food_delivery_system.services;


import org.example.food_delivery_system.exceptions.FoodItemNotFoundException;
import org.example.food_delivery_system.exceptions.InvalidRoleForCustomerException;
import org.example.food_delivery_system.exceptions.UserNotFoundExcepion;
import org.example.food_delivery_system.models.Cart;

public interface CartService {
    Cart addFoodItem(long customerId, long foodItemId, int quantity) throws UserNotFoundExcepion, InvalidRoleForCustomerException, FoodItemNotFoundException;
}
