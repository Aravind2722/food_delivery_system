package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FoodMenu extends BaseModel {
    @OneToOne
    @JoinColumn
    private Restaurant restaurant;

    @OneToMany
    private List<FoodItem> foodItems;
}
