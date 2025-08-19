package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.AdminAlreadyRegisteredWithRestaurantException;
import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.*;
import org.example.food_delivery_system.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
