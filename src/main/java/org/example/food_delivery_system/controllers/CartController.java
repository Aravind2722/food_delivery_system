package org.example.food_delivery_system.controllers;

import org.example.food_delivery_system.dtos.AddFoodItemIntoCartRequestDto;
import org.example.food_delivery_system.dtos.AddFoodItemIntoCartResponseDto;
import org.example.food_delivery_system.exceptions.FoodItemNotFoundException;
import org.example.food_delivery_system.exceptions.InvalidRoleForCustomerException;
import org.example.food_delivery_system.exceptions.UserNotFoundExcepion;
import org.example.food_delivery_system.models.Cart;
import org.example.food_delivery_system.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/{customerId}/items")
public class CartController {
    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("")
    public ResponseEntity<AddFoodItemIntoCartResponseDto> addFoodItemIntoCart(@PathVariable long customerId, @RequestBody AddFoodItemIntoCartRequestDto requestDto) throws UserNotFoundExcepion, InvalidRoleForCustomerException, FoodItemNotFoundException {
        Cart cart = cartService.addFoodItem(customerId, requestDto.getFoodItemId(), requestDto.getQuantity());
        AddFoodItemIntoCartResponseDto responseDto = new AddFoodItemIntoCartResponseDto();
        responseDto.setCartItemDtos(cart.getCartItems().stream().map(cartItem -> cartItem.toDto()).toList());
        responseDto.setTotal(cart.getPrice());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @ExceptionHandler({UserNotFoundExcepion.class, FoodItemNotFoundException.class})
    public ResponseEntity<String> handleUserNotFoundExcepionAndFoodItemNotFoundException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidRoleForCustomerException.class)
    public ResponseEntity<String> handleInvalidRoleForCustomerException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

}
