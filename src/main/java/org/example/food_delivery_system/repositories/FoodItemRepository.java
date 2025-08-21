package org.example.food_delivery_system.repositories;

import org.example.food_delivery_system.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Optional<FoodItem> findById(long id);
}