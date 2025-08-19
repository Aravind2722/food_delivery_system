package org.example.food_delivery_system.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem extends BaseModel {
    @OneToOne
    private FoodItem foodItem;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
}
