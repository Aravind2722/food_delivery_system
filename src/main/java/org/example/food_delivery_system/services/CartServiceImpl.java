package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.FoodItemNotFoundException;
import org.example.food_delivery_system.exceptions.InvalidRoleForCustomerException;
import org.example.food_delivery_system.exceptions.UserNotFoundExcepion;
import org.example.food_delivery_system.models.*;
import org.example.food_delivery_system.repositories.CartRepository;
import org.example.food_delivery_system.repositories.CustomerRepository;
import org.example.food_delivery_system.repositories.FoodItemRepository;
import org.example.food_delivery_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final FoodItemRepository foodItemRepository;

    public CartServiceImpl(CustomerRepository customerRepository,
                           CartRepository cartRepository,
                           FoodItemRepository foodItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public Cart addFoodItem(long customerId, long foodItemId, int quantity) throws UserNotFoundExcepion, InvalidRoleForCustomerException, FoodItemNotFoundException {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) throw new UserNotFoundExcepion("Customer "+customerId+" not found.");
        Customer customer = customerOptional.get();
        if (!customer.getUser().getRole().equals(Role.CUSTOMER)) throw new InvalidRoleForCustomerException("User "+ customer.getUser().getName()+"'s role does not match a Customer.");

        Optional<Cart> cartOptional = cartRepository.findByCustomer(customer);
        if (cartOptional.isEmpty()) throw new RuntimeException("Cart not found.");
        Cart cart = cartOptional.get();

        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(foodItemId);
        if (foodItemOptional.isEmpty()) throw new FoodItemNotFoundException("Food item "+foodItemId+" not found.");
        FoodItem foodItem = foodItemOptional.get();

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems == null) cartItems = new ArrayList<>();
        boolean foodItemPresent = false;
        CartItem cartItem = new CartItem();
        for (CartItem oldCartItem : cartItems) {
            if ((long) oldCartItem.getFoodItem().getId() == (long) foodItemId) {
                cartItem = oldCartItem;
                foodItemPresent = true;
                break;
            }
        }

        double price = foodItem.getPrice() * quantity;
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(cartItem.getQuantity()+quantity);
        cartItem.setCart(cart);
        cartItem.setPrice(cartItem.getPrice() + price);
        if (!foodItemPresent) cart.getCartItems().add(cartItem);

        cart.setPrice(cart.getPrice() + price);

        return cartRepository.save(cart);
    }
}
