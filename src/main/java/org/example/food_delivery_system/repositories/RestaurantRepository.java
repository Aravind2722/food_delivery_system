package org.example.food_delivery_system.repositories;

import org.example.food_delivery_system.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(long id);
}
