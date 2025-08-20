package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Getter
@Setter
public class FoodMenu extends BaseModel {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodItems;
}
