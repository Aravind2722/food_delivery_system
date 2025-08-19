package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.*;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Cuisine;
import org.example.food_delivery_system.models.DietaryPreference;
import org.example.food_delivery_system.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant registerRestaurant(
            String adminName,
            String adminPhoneNumber,
            String adminEmail,
            String password,
            String restaurantName,
            String description,
            String restaurantEmail,
            String restaurantPhoneNumber,
            Address address,
            String image,
            List<Cuisine> cuisines
    ) throws AdminAlreadyRegisteredWithRestaurantException;

    Restaurant addFoodItem(
            long restaurantId,
            long adminId,
            String foodName,
            String description,
            DietaryPreference dietaryPreference,
            double price,
            String image
    ) throws RestaurantNotFoundException, InvalidAdminException;
}
