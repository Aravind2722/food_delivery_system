package org.example.food_delivery_system.controllers;

import org.example.food_delivery_system.dtos.RegisterRestaurantRequestDto;
import org.example.food_delivery_system.dtos.RegisterRestaurantResponseDto;
import org.example.food_delivery_system.exceptions.AdminAlreadyRegisteredWithRestaurantException;
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

    @ExceptionHandler(AdminAlreadyRegisteredWithRestaurantException.class)
    public ResponseEntity<String> handleAdminAlreadyRegisteredWithRestaurantException(AdminAlreadyRegisteredWithRestaurantException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
