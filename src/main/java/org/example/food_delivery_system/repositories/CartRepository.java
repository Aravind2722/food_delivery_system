package org.example.food_delivery_system.repositories;

import org.example.food_delivery_system.models.Cart;
import org.example.food_delivery_system.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    Optional<Cart> findByCustomer(Customer customer);
}
