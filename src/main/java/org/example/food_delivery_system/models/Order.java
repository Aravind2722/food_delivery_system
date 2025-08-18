package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {
    @OneToOne
    private Cart cart;
    @OneToOne
    private Bill bill;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
}
