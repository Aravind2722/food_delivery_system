package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Cuisine;

import java.util.List;

@Getter
@Setter
public class RegisterRestaurantRequestDto {
    private String adminName;
    private String adminPhoneNumber;
    private String adminEmail;
    private String password;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String restaurantEmail;
    private String description;
    private List<Cuisine> cuisines;
    private Address address;
    private String image;
}
