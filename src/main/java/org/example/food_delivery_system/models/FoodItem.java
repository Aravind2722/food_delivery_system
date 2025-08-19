package org.example.food_delivery_system.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoodItem extends BaseModel {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DietaryPreference dietaryPreference;
    private double rating;
    private double price;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private FoodMenu foodMenu;
}
