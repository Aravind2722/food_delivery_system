package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends BaseModel {
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;
    private double price;
}
