package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.AdminAlreadyRegisteredWithRestaurantException;
import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Cuisine;
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
}
