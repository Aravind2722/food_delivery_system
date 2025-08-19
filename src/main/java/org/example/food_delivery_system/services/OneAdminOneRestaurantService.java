package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.*;
import org.example.food_delivery_system.models.*;
import org.example.food_delivery_system.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OneAdminOneRestaurantService implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    @Autowired
    public OneAdminOneRestaurantService(RestaurantRepository restaurantRepository, OneRoleOneUserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }
    @Override
    public Restaurant registerRestaurant(
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
    ) throws AdminAlreadyRegisteredWithRestaurantException {
        User admin;
        try {
            admin = userService.signUpUser(adminName, adminEmail, adminPhoneNumber, Role.RESTAURANT_ADMIN, password);
        }catch (PhoneNumberAlreadyExistsException | EmailAlreadyExistsException exception) {
            throw new AdminAlreadyRegisteredWithRestaurantException("Admin already registered with a restaurant!");
        }


        Restaurant restaurant = new Restaurant();
        restaurant.setAdmin(admin);
        restaurant.setName(restaurantName);
        restaurant.setEmail(restaurantEmail);
        restaurant.setDescription(description);
        restaurant.setPhoneNumber(restaurantPhoneNumber);
        restaurant.setCuisines(cuisines);
        restaurant.setAddress(address);
        restaurant.setImage(image);

        return restaurantRepository.save(restaurant);

    }

    @Override
    public Restaurant addFoodItem(long restaurantId, long adminId, String foodName, String description, DietaryPreference dietaryPreference, double price, String image) throws RestaurantNotFoundException, InvalidAdminException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isEmpty()) throw new RestaurantNotFoundException("Restaurant "+restaurantId+" not found.");
        Restaurant restaurant = restaurantOptional.get();

        if ((long)restaurant.getAdmin().getId() != (long)adminId) throw new InvalidAdminException("Invalid admin "+adminId+".");

        FoodMenu foodMenu = restaurant.getFoodMenu();
        if (foodMenu == null) {
            foodMenu = new FoodMenu();
            foodMenu.setRestaurant(restaurant);
        }

        List<FoodItem> foodItems = foodMenu.getFoodItems();
        if (foodItems == null) foodItems = new ArrayList<>();

        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodName);
        foodItem.setDescription(description);
        foodItem.setImage(image);
        foodItem.setDietaryPreference(dietaryPreference);
        foodItem.setPrice(price);
        foodItem.setFoodMenu(foodMenu);

        foodItems.add(foodItem);
        foodMenu.setFoodItems(foodItems);
        restaurant.setFoodMenu(foodMenu);
        return restaurantRepository.save(restaurant);
    }
}
