package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FoodMenu extends BaseModel {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @OneToMany
    private List<FoodItem> foodItems;
}
