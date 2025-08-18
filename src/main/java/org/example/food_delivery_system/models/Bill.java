package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bill extends BaseModel {
    private double totalAmount;
    private double tax;
    private double discount;
    private double tip;
    private double deliveryCharges;
}
