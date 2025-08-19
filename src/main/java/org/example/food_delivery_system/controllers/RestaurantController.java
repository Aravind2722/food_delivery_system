package org.example.food_delivery_system.controllers;

import org.example.food_delivery_system.dtos.AddFoodItemRequestDto;
import org.example.food_delivery_system.dtos.AddFoodItemResponseDto;
import org.example.food_delivery_system.dtos.RegisterRestaurantRequestDto;
import org.example.food_delivery_system.dtos.RegisterRestaurantResponseDto;
import org.example.food_delivery_system.exceptions.AdminAlreadyRegisteredWithRestaurantException;
import org.example.food_delivery_system.exceptions.InvalidAdminException;
import org.example.food_delivery_system.exceptions.RestaurantNotFoundException;
import org.example.food_delivery_system.models.Restaurant;
import org.example.food_delivery_system.services.OneAdminOneRestaurantService;
import org.example.food_delivery_system.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(OneAdminOneRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("")
    public ResponseEntity<RegisterRestaurantResponseDto> registerRestaurant(@RequestBody  RegisterRestaurantRequestDto requestDto) throws AdminAlreadyRegisteredWithRestaurantException {
        Restaurant restaurant = restaurantService.registerRestaurant(
                requestDto.getAdminName(),
                requestDto.getAdminPhoneNumber(),
                requestDto.getAdminEmail(),
                requestDto.getPassword(),
                requestDto.getRestaurantName(),
                requestDto.getDescription(),
                requestDto.getRestaurantEmail(),
                requestDto.getRestaurantPhoneNumber(),
                requestDto.getAddress(),
                requestDto.getImage(),
                requestDto.getCuisines()
        );
        RegisterRestaurantResponseDto responseDto = new RegisterRestaurantResponseDto();
        responseDto.setRestaurantId(restaurant.getId());
        responseDto.setAdminId(restaurant.getAdmin().getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/food-menu")
    public ResponseEntity<AddFoodItemResponseDto> addFoodItem(@RequestBody AddFoodItemRequestDto requestDto) throws RestaurantNotFoundException, InvalidAdminException {
        Restaurant restaurant = restaurantService.addFoodItem(requestDto.getRestaurantId(), requestDto.getAdminId(), requestDto.getFoodName(), requestDto.getDescription(), requestDto.getDietaryPreference(), requestDto.getPrice(), requestDto.getImage());
        AddFoodItemResponseDto responseDto = new AddFoodItemResponseDto();
        responseDto.setFoodItemId(restaurant.getFoodMenu().getFoodItems().getLast().getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @ExceptionHandler(AdminAlreadyRegisteredWithRestaurantException.class)
    public ResponseEntity<String> handleAdminAlreadyRegisteredWithRestaurantException(AdminAlreadyRegisteredWithRestaurantException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<String> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(InvalidAdminException.class)
    public ResponseEntity<String> handleInvalidAdminException(InvalidAdminException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }
}
